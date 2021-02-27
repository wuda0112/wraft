package com.wuda.raft.core;

import com.wuda.raft.grpc.codegen.RpcServiceGrpc;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 负责领导者选举.
 *
 * @author wuda
 */
public class LeaderElectionService {

    private String topic = "LeaderElection";

    private final static Logger logger = LoggerFactory.getLogger(LeaderElectionService.class);

    /**
     * 持有{@link BambooState}.
     */
    private BambooState bambooState;
    /**
     * configuration.
     */
    private Configuration configuration;
    /**
     * 用于发送心跳.
     */
    private LogReplicationService logReplicationService;

    private ThreadPoolExecutor threadPoolExecutor;

    private RequestVoteResults requestVoteResults;

    /**
     * 构建实例.
     *
     * @param configuration         configuration
     * @param bambooState           {@link Bamboo}内部状态
     * @param logReplicationService 用于发送心跳
     */
    public LeaderElectionService(Configuration configuration, BambooState bambooState, LogReplicationService logReplicationService) {
        this.configuration = configuration;
        this.bambooState = bambooState;
        this.threadPoolExecutor = new ThreadPoolExecutor(configuration.getBambooLocatorsListExclusiveSelf().size() + 1,
                configuration.getBambooLocators().size() + 10,
                10,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new BasicThreadFactory.Builder()
                        .daemon(true)
                        .namingPattern("LeaderElection-%d")
                        .build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        this.requestVoteResults = new RequestVoteResults(bambooState.getTerm());
        this.logReplicationService = logReplicationService;
    }

    /**
     * 启动服务.
     */
    public void start() {

    }

    /**
     * 停止.
     */
    public void stop() {

    }


    /**
     * 向集群中的其他节点发送投票请求.
     *
     * @param bambooLocator 用于定位其他节点
     * @param parameter     请求参数
     * @return 投票结果
     */
    public RequestVoteResult requestVoteRpc(BambooLocator bambooLocator, RequestVoteParameter parameter) {
        try {
            RpcServiceGrpc.RpcServiceBlockingStub stub = RpcStubCache.getStub(bambooLocator);

            com.wuda.raft.grpc.codegen.RequestVoteParameter requestVoteParameter = com.wuda.raft.grpc.codegen.RequestVoteParameter.newBuilder()
                    .setCandidateTerm(parameter.getCandidateTerm())
                    .setCandidateId(parameter.getCandidateId())
                    .build();
            com.wuda.raft.grpc.codegen.RequestVoteResult requestVoteResult = stub.withDeadlineAfter(50, TimeUnit.MILLISECONDS)
                    .requestVote(requestVoteParameter);

            return new RequestVoteResult(requestVoteResult.getBambooId(), requestVoteResult.getTerm(), requestVoteResult.getVoteGranted());
        } catch (Exception e) {
            logger.warn("{}candidateTerm = {},获取节点【{}】的投票结果异常", getLogHeader(), parameter.getCandidateTerm(), bambooLocator.getHost() + ":" + bambooLocator.getPort(), e);
            return null;
        }
    }

    /**
     * 触发leader election.
     */
    public void triggerElection() {
        try {
            Future f = threadPoolExecutor.submit(this::beginElection);
            f.get(Utils.multiply(Global.getLeaderElectionTimer().getTimeoutMillis(), 0.9), TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            logger.warn("{}选举花费的时间超过election timeout value", getLogHeader(), e);
        } catch (InterruptedException e) {
            logger.warn("{}选举过程中,Thread Interrupted", getLogHeader(), e);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动选举.注意该方法会在{@link LeaderElectionTimer}持有的线程中执行.
     */
    private void beginElection() {
        logger.info("{}leader election 准备...", getLogHeader());
        BambooStatePortionSnapshot bambooStatePortionSnapshot = bambooState.tryBecomeCandidate(Global.getLeaderElectionTimer().getTerm());
        if (bambooStatePortionSnapshot == null) {
            logger.warn("{}不能转换成Candidate", getLogHeader());
            Global.getLeaderElectionTimer().changeTerm(bambooState.getTerm());
            return;
        }
        long candidateTerm = bambooStatePortionSnapshot.getTerm();
        logger.info("{}candidateTerm = {}, leader election 开始...", getLogHeader(), candidateTerm);
        this.requestVoteResults.reset(bambooStatePortionSnapshot.getTerm());
        List<BambooLocator> exclusiveSelfBambooLocatorList = configuration.getBambooLocatorsListExclusiveSelf();
        RequestVoteParameter parameter = new RequestVoteParameter(bambooStatePortionSnapshot.getTerm(), configuration.getMyId());
        List<Future<RequestVoteResult>> futures = new ArrayList<>(exclusiveSelfBambooLocatorList.size());
        // 并行向其他节点发起投票请求
        for (BambooLocator bambooLocator : exclusiveSelfBambooLocatorList) {
            Future<RequestVoteResult> future = threadPoolExecutor.submit(() -> requestVoteRpc(bambooLocator, parameter));
            futures.add(future);
        }
        for (Future<RequestVoteResult> future : futures) {
            RequestVoteResult requestVoteResult;
            try {
                requestVoteResult = future.get(Utils.multiply(Global.getLeaderElectionTimer().getTimeoutMillis(), 0.5), TimeUnit.MILLISECONDS);
                if (requestVoteResult == null) {
                    logger.warn("{}candidateTerm = {}, 在规定时间范围内没有收到其他节点的投票回复", getLogHeader(), candidateTerm);
                    continue;
                }
            } catch (Exception e) {
                logger.warn("{}candidateTerm = {}, 等待其他节点返回投票结果时异常", getLogHeader(), candidateTerm, e);
                continue;
            }
            requestVoteResults.add(requestVoteResult);
            if (bambooState.tryUpdateIfOthersMoreUpToDate(requestVoteResult.getTerm(), BambooRole.FOLLOWER, null) != null) {
                // 结束本次选举
                logger.info("{}candidateTerm = {}, 得到的投票结果表明其他节点更up-to-date,自动转为follower", getLogHeader(), candidateTerm);
                break;
            } else {
                RequestVoteResults.Explain explain = requestVoteResults.explain(bambooState.getTerm());
                if (explain.isWin()) {
                    if (bambooState.setRole(BambooRole.LEADER)) {
                        logger.info("{}candidateTerm = {}, wins leader election", getLogHeader(), candidateTerm);
                        // heartbeat
                        logReplicationService.sendHeartbeat();
                    }
                }
            }
        }
    }

    /**
     * 收到其他节点的投票请求.
     *
     * @param parameter 请求参数
     * @return 投票结果
     */
    public RequestVoteResult onReceivedRequestVoteRpc(RequestVoteParameter parameter) {
        boolean voteGranted = false;
        if (bambooState.tryUpdateIfOthersMoreUpToDate(parameter.getCandidateTerm(), BambooRole.FOLLOWER, parameter.getCandidateId()) != null) {
            voteGranted = true;
            logger.info("{}candidateTerm = {}, 节点【{}】更up-to-date,投给它", getLogHeader(), parameter.getCandidateTerm(), parameter.getCandidateId());
        }
        return new RequestVoteResult(configuration.getMyId(), bambooState.getTerm(), voteGranted);
    }

    /**
     * 日志头信息
     *
     * @return 日志头信息
     */
    private String getLogHeader() {
        return Utils.getLogHeader("LeaderElection", configuration.getMyId(), bambooState.getTerm());
    }
}
