package com.wuda.raft.core;

/**
 * 全局信息,比如集群中有多少节点.
 *
 * @author wuda
 */
public class Global {

    /**
     * 持有配置信息.
     */
    private static Configuration configuration;

    /**
     * 持有leader election timer.
     */
    private static LeaderElectionTimer leaderElectionTimer;

    /**
     * 持有heartbeat timer.
     */
    private static HeartbeatTimer heartbeatTimer;

    /**
     * 禁止实例化.
     */
    private Global() {

    }

    /**
     * 设置配置信息.
     *
     * @param configuration 配置信息
     */
    static void setConfiguration(Configuration configuration) {
        Global.configuration = configuration;
    }

    /**
     * 设置leader election timer到全局配置中.
     *
     * @param leaderElectionTimer leader election timer
     */
    static void setLeaderElectionTimer(LeaderElectionTimer leaderElectionTimer) {
        Global.leaderElectionTimer = leaderElectionTimer;
    }

    /**
     * 设置heartbeat timer到全局配置中.
     *
     * @param heartbeatTimer heartbeat timer
     */
    static void setHeartbeatTimer(HeartbeatTimer heartbeatTimer) {
        Global.heartbeatTimer = heartbeatTimer;
    }

    /**
     * 集群中节点总数.
     *
     * @return 节点总数
     */
    public static int bambooTotal() {
        return getConfiguration().getBambooLocators().size();
    }

    /**
     * get configuration
     *
     * @return {@link Configuration}
     */
    public static Configuration getConfiguration() {
        return configuration;
    }

    /**
     * get leader election timer.
     *
     * @return {@link LeaderElectionTimer}
     */
    public static LeaderElectionTimer getLeaderElectionTimer() {
        return leaderElectionTimer;
    }

    /**
     * get heartbeat timer.
     *
     * @return {@link HeartbeatTimer}
     */
    public static HeartbeatTimer getHeartbeatTimer() {
        return heartbeatTimer;
    }


}
