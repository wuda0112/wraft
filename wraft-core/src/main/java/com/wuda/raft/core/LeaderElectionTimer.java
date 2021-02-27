package com.wuda.raft.core;

/**
 * randomized schedule to elect leaders.
 *
 * @author wuda
 */
public class LeaderElectionTimer extends OneThreadOneTaskScheduler {

    /**
     * 构造实例.任务的周期执行时间是随机的.
     *
     * @param task                             执行的任务
     * @param timeoutRangeStartInclusiveMillis 随机选择超时时间的最小值.
     * @param timeoutRangeEndInclusiveMillis   随机选择超时时间的最大值.
     */
    public LeaderElectionTimer(Runnable task, long timeoutRangeStartInclusiveMillis, long timeoutRangeEndInclusiveMillis) {
        super("LeaderElection", task, timeoutRangeStartInclusiveMillis, timeoutRangeEndInclusiveMillis);
    }
}
