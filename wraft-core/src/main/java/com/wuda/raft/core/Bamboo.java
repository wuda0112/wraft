package com.wuda.raft.core;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 竹筏由多根竹子捆绑在一起组成,因此这个类代表Raft中的每根竹子.
 *
 * @author wuda
 */
public class Bamboo {

    private static Logger logger = LoggerFactory.getLogger(Bamboo.class);
    /**
     * 内部状态.
     */
    private BambooState bambooState;
    /**
     * configuration.
     */
    private Configuration configuration;
    /**
     * leader election timer.
     */
    private LeaderElectionTimer leaderElectionTimer;
    /**
     * heartbeat timer.
     */
    private HeartbeatTimer heartbeatTimer;
    /**
     * leader election service.
     */
    private LeaderElectionService leaderElectionService;
    /**
     * log replication service.
     */
    private LogReplicationService logReplicationService;

    /**
     * 构造实例.
     *
     * @param configuration configuration
     */
    public Bamboo(Configuration configuration) {
        this.bambooState = new BambooState();
        this.configuration = configuration;
        Global.setConfiguration(configuration);
        this.logReplicationService = new LogReplicationService(configuration, bambooState);
        this.leaderElectionService = new LeaderElectionService(configuration, bambooState, logReplicationService);
        this.leaderElectionTimer = new LeaderElectionTimer(leaderElectionService::triggerElection, configuration.getLeaderElectionTimeoutRangeStartInclusiveMillis(), configuration.getLeaderElectionTimeoutRangeEndInclusiveMillis());
        this.heartbeatTimer = new HeartbeatTimer(logReplicationService::sendHeartbeat, configuration.getHeartbeatTimeout(), configuration.getHeartbeatTimeout());
        Global.setLeaderElectionTimer(this.leaderElectionTimer);
        Global.setHeartbeatTimer(this.heartbeatTimer);
        RpcStubCache.init(configuration.getBambooLocators());
        this.leaderElectionTimer.start(this.bambooState.getTerm());
    }

    /**
     * 启动.
     */
    public void start() {
        leaderElectionService.start();
        int port = configuration.getMySelf().getPort();
        try {
            final Server server = ServerBuilder
                    .forPort(port)
                    .addService(new RpcServiceImpl(this.leaderElectionService, this.logReplicationService))
                    .build()
                    .start();
            logger.info("gRPC Server started, listening on " + port);
            server.awaitTermination();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * stop.
     */
    public void stop() {
        leaderElectionService.stop();
    }

    /**
     * 向集群中的其他节点发送 AppendEntries RPC.
     */
    public void appendEntriesRPC() {

    }

    /**
     * 从集群中的其他节点收到 AppendEntries RPC.
     */
    public void onReceivedAppendEntriesRPC() {

    }
}
