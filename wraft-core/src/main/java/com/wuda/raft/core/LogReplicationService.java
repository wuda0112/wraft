package com.wuda.raft.core;

import com.google.protobuf.ByteString;
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
 * 负责log replication.
 *
 * @author wuda
 */
public class LogReplicationService {

    private final static Logger logger = LoggerFactory.getLogger(LogReplicationService.class);

    /**
     * 配置信息.
     */
    private Configuration configuration;
    /**
     * 持有{@link BambooState}.
     */
    private BambooState bambooState;

    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 构造实例.
     *
     * @param configuration configuration
     * @param bambooState   {@link BambooState}
     */
    public LogReplicationService(Configuration configuration, BambooState bambooState) {
        this.configuration = configuration;
        this.bambooState = bambooState;
        int bambooSize = configuration.getBambooLocators().size();
        this.threadPoolExecutor = new ThreadPoolExecutor(bambooSize + 1,
                bambooSize * 4,
                10,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new BasicThreadFactory.Builder()
                        .daemon(true)
                        .namingPattern("LogReplication-%d")
                        .build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * AppendEntries RPC. Invoked by leader to replicate log entries ; also used as heartbeat.
     *
     * @param bambooLocator 发往这个bamboo(节点)
     * @param parameter     parameter
     * @return result, <code>null</code>-如果远程方法调用出现异常
     */
    public AppendEntryResult appendEntriesRpc(BambooLocator bambooLocator, AppendEntryParameter parameter) {
        try {
            RpcServiceGrpc.RpcServiceBlockingStub stub = RpcStubCache.getStub(bambooLocator);

            com.wuda.raft.grpc.codegen.AppendEntryParameter appendEntryParameter = com.wuda.raft.grpc.codegen.AppendEntryParameter.newBuilder()
                    .setTerm(parameter.getTerm())
                    .setLeaderId(parameter.getLeaderId())
                    .setEntries(ByteString.copyFrom(parameter.getEntries()))
                    .build();
            com.wuda.raft.grpc.codegen.AppendEntryResult appendEntryResult = stub.withDeadlineAfter(50, TimeUnit.MILLISECONDS).
                    appendEntriesRpc(appendEntryParameter);

            return new AppendEntryResult(appendEntryResult.getTerm(), appendEntryResult.getSuccess());
        } catch (Exception e) {
            logger.warn("{}appendEntriesRpc,目标节点【{}】", getLogHeader(), bambooLocator.getHost() + ":" + bambooLocator.getPort(), e);
            return null;
        }
    }

    /**
     * 当接收到其他节点的AppendEntries RPC 请求.
     *
     * @param parameter parameter
     * @return 响应结果
     */
    public AppendEntryResult onReceivedAppendEntriesRpc(AppendEntryParameter parameter) {
        bambooState.tryUpdateIfOthersMoreUpToDate(parameter.getTerm(), BambooRole.FOLLOWER, null);
        if (parameter.isHeartbeat()) {
            logger.info("{}收到心跳", getLogHeader());
        }
        Global.getLeaderElectionTimer().reset(bambooState.getTerm());
        return new AppendEntryResult(bambooState.getTerm(), true);
    }

    /**
     * 向集群中其他所有节点发送心跳.
     */
    public void sendHeartbeat() {
        long heartbeatTerm = bambooState.getTerm();
        Future<Integer> futureF = threadPoolExecutor.submit(() -> {
            AppendEntryParameter appendEntryParameter = AppendEntryParameter.createHeartbeat(heartbeatTerm, configuration.getMyId());
            List<BambooLocator> exclusiveSelfBambooLocatorList = configuration.getBambooLocatorsListExclusiveSelf();
            List<Future<AppendEntryResult>> futures = new ArrayList<>(exclusiveSelfBambooLocatorList.size());
            for (BambooLocator bambooLocator : exclusiveSelfBambooLocatorList) {
                futures.add(threadPoolExecutor.submit(() -> appendEntriesRpc(bambooLocator, appendEntryParameter)));
                logger.info("{}heartbeatTerm = {},向节点 {} 发送心跳", getLogHeader(), heartbeatTerm, bambooLocator.getId());
            }
            Integer receivedCount = 0;
            for (Future<AppendEntryResult> future : futures) {
                AppendEntryResult appendEntryResult;
                try {
                    appendEntryResult = future.get(Utils.multiply(Global.getHeartbeatTimer().getTimeoutMillis(), 0.7), TimeUnit.MILLISECONDS);
                    receivedCount++;
                } catch (Exception e) {
                    logger.warn("{}heartbeatTerm = {}, 获取心跳结果异常", getLogHeader(), heartbeatTerm, e);
                    continue;
                }
                if (appendEntryResult != null) {
                    if (bambooState.tryUpdateIfOthersMoreUpToDate(appendEntryResult.getTerm(), BambooRole.FOLLOWER, null) != null) {
                        logger.info("{}heartbeatTerm = {}, 收到的心跳结果表明其他节点更up-to-date,自动转为follower", getLogHeader(), heartbeatTerm);
                        break;
                    }
                }
            }
            return receivedCount;
        });
        try {
            futureF.get(Utils.multiply(Global.getHeartbeatTimer().getTimeoutMillis(), 0.95), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            logger.warn("{}heartbeatTerm = {}, 心跳发送和接收时Interrupted", getLogHeader(), heartbeatTerm, e);
        } catch (ExecutionException e) {
            logger.warn("{}heartbeatTerm = {}, 心跳发送和接收时ExecutionException", getLogHeader(), heartbeatTerm, e);
        } catch (TimeoutException e) {
            logger.warn("{}heartbeatTerm = {}, 在指定时间范围内未完成所有心跳发送和接收", getLogHeader(), heartbeatTerm, e);
        }
    }

    /**
     * 日志头信息
     *
     * @return 日志头信息
     */
    private String getLogHeader() {
        return Utils.getLogHeader("LogReplication", configuration.getMyId(), bambooState.getTerm());
    }
}
