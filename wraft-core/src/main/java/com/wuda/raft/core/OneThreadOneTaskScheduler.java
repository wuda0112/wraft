package com.wuda.raft.core;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 定期执行任务.就像名称所描述的一样,该Scheduler只有一个线程,并且只有一个任务被定期执行.
 *
 * @author wuda
 */
public abstract class OneThreadOneTaskScheduler {

    private String threadName;

    private static Logger logger = LoggerFactory.getLogger(OneThreadOneTaskScheduler.class);
    /**
     * 该scheduler正在为哪个term提供计时服务.
     */
    private long term;
    /**
     * 随机选择超时时间的最小值.
     */
    private long timeoutRangeStartInclusiveMillis;
    /**
     * 随机选择超时时间的最大值.
     */
    private long timeoutRangeEndInclusiveMillis;
    /**
     * 是否已经shutdown.
     */
    private boolean shutdown = false;

    private ScheduledThreadPoolExecutor scheduler;
    /**
     * 执行的任务
     */
    private Runnable task;

    private ScheduledFuture taskFuture;

    private long timeoutMillis;

    private Lock lock = new ReentrantLock();

    /**
     * 构造实例.任务的周期执行时间是随机的.
     *
     * @param threadName                       线程名称
     * @param timeoutRangeStartInclusiveMillis 随机选择超时时间的最小值.
     * @param timeoutRangeEndInclusiveMillis   随机选择超时时间的最大值.
     * @param task                             执行的任务
     */
    public OneThreadOneTaskScheduler(String threadName, Runnable task, long timeoutRangeStartInclusiveMillis, long timeoutRangeEndInclusiveMillis) {
        // 线程池大小.
        int threadPoolSize = 1;
        this.task = Objects.requireNonNull(task);
        if (timeoutRangeStartInclusiveMillis < 1 || timeoutRangeEndInclusiveMillis < 1) {
            throw new IllegalArgumentException("timeoutRangeStartInclusiveMillis = " + timeoutRangeStartInclusiveMillis + ", timeoutRangeEndInclusiveMillis = " + timeoutRangeEndInclusiveMillis + ", 超时时间必须大于0");
        }
        this.timeoutRangeStartInclusiveMillis = timeoutRangeStartInclusiveMillis;
        this.timeoutRangeEndInclusiveMillis = timeoutRangeEndInclusiveMillis;
        this.scheduler = new ScheduledThreadPoolExecutor(threadPoolSize,
                new BasicThreadFactory.Builder()
                        .daemon(true)
                        .namingPattern(threadName + "-%d")
                        .build(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        this.scheduler.setMaximumPoolSize(threadPoolSize);
        this.scheduler.setRemoveOnCancelPolicy(true);
        this.threadName = threadName;
    }


    /**
     * 重新排期任务.如果任务正在执行,则执行该任务会的线程会收到{@link Thread#interrupt()}信号,如果任务忽略该interrupt信号,
     * 则正在执行的任务会正常执行完成; 如果任务还没有执行,则该任务被remove. 不管是以上的哪种情况,最后都会重新排期任务.
     *
     * @param term 重置后的Scheduler为哪个term提供服务
     */
    public void reset(long term) {
        ensureNotShutdown();
        if (lock.tryLock()) {
            try {
                if (taskFuture != null) {
                    taskFuture.cancel(true);
                }
                scheduler.purge();
                scheduler.getQueue().clear();
                start0(term);
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * 启动.只能被调用一次,后续可以使用{@link #reset(long)}来重新排期任务.
     *
     * @param term Scheduler启动后为哪个term提供计时服务
     */
    public void start(long term) {
        if (lock.tryLock()) {
            try {
                if (taskFuture != null) {
                    throw new RuntimeException(threadName + " Scheduler 已经启动,可以使用reset(term)方法重置");
                }
                start0(term);
            } finally {
                lock.unlock();
            }
        }
    }

    private void start0(long term) {
        ensureNotShutdown();
        this.term = term;
        timeoutMillis = randomTimeout();
        taskFuture = scheduler.scheduleAtFixedRate(task, timeoutMillis, timeoutMillis, TimeUnit.MILLISECONDS);
        logger.info("{}start0 方法调用, task count = {}, timeout = {}", Utils.getLogHeader(threadName, Global.getConfiguration().getMyId(), term), scheduler.getQueue().size(), timeoutMillis);
    }

    /**
     * 停止排期任务,后续还可以调用{@link #reset(long)}方法继续排期任务.注意和{@link #shutdownNow()}的区别.
     */
    public void stop() {
        ensureNotShutdown();
        if (taskFuture != null) {
            taskFuture.cancel(true);
        }
        scheduler.purge();
        logger.info("{}stop 方法调用, task count = {}", Utils.getLogHeader(threadName, Global.getConfiguration().getMyId(), term), scheduler.getQueue().size());
    }

    /**
     * shutdown.清理掉线程,任务等各种资源,后续不能再排期任务.
     */
    public void shutdownNow() {
        ensureNotShutdown();
        shutdown = true;
        if (taskFuture != null) {
            taskFuture.cancel(true);
        }
        scheduler.shutdownNow();
    }

    /**
     * 随机分配超时时间.
     *
     * @return 超时时间, 毫秒为单位
     */
    private long randomTimeout() {
        long first = RandomUtils.nextLong(timeoutRangeStartInclusiveMillis, timeoutRangeEndInclusiveMillis + 1);
        return RandomUtils.nextLong(first, timeoutRangeEndInclusiveMillis + 1);
    }

    /**
     * 获取本次随机的timeout value.
     *
     * @return timeout value
     */
    public long getTimeoutMillis() {
        return timeoutMillis;
    }

    /**
     * 获取Scheduler正在为哪个term提供计时服务.
     *
     * @return term
     */
    public long getTerm() {
        return term;
    }

    /**
     * 改变term.
     *
     * @param term term
     */
    public void changeTerm(long term) {
        this.term = term;
    }

    /**
     * 检测是否已经停止.
     */
    private void ensureNotShutdown() {
        if (shutdown) {
            throw new RuntimeException(threadName + " Scheduler 已经shutdown");
        }
    }
}
