package com.wuda.raft.core;

/**
 * 用于定期发送心跳.
 *
 * @author wuda
 */
public class HeartbeatTimer extends OneThreadOneTaskScheduler {


    /**
     * 构造实例.任务的周期执行时间是随机的.
     *
     * @param task                             执行的任务
     * @param timeoutRangeStartInclusiveMillis 随机选择超时时间的最小值.
     * @param timeoutRangeEndInclusiveMillis   随机选择超时时间的最大值.
     */
    public HeartbeatTimer(Runnable task, long timeoutRangeStartInclusiveMillis, long timeoutRangeEndInclusiveMillis) {
        super("Heartbeat", task, timeoutRangeStartInclusiveMillis, timeoutRangeEndInclusiveMillis);
    }
}
