package com.wuda.raft.core;

import java.util.List;

/**
 * 配置信息.
 *
 * @author wuda
 */
public interface Configuration {

    /**
     * 获取election timeout,随机选择超时时间的最小值.
     *
     * @return election timeout,随机选择超时时间的最小值.
     */
    long getLeaderElectionTimeoutRangeStartInclusiveMillis();

    /**
     * 设置election timeout,随机选择超时时间的最小值.
     * *
     *
     * @param leaderElectionTimeoutRangeStartInclusiveMillis election timeout,随机选择超时时间的最小值.
     */
    void setLeaderElectionTimeoutRangeStartInclusiveMillis(long leaderElectionTimeoutRangeStartInclusiveMillis);

    /**
     * 获取election timeout,随机选择超时时间的最大值.
     *
     * @return election timeout,随机选择超时时间的最大值.
     */
    long getLeaderElectionTimeoutRangeEndInclusiveMillis();

    /**
     * 设置election timeout,随机选择超时时间的最大值.
     *
     * @param leaderElectionTimeoutRangeEndInclusiveMillis election timeout,随机选择超时时间的最大值.
     */
    void setLeaderElectionTimeoutRangeEndInclusiveMillis(long leaderElectionTimeoutRangeEndInclusiveMillis);

    /**
     * 获取集群中的所有节点的定位信息.
     *
     * @return 集群中的所有节点, key是bamboo的id
     */
    List<BambooLocator> getBambooLocators();

    /**
     * 设置集群中的所有节点的定位信息.
     *
     * @param bambooLocators 集群中的所有节点
     */
    void setBambooLocators(List<BambooLocator> bambooLocators);

    /**
     * 获取当前{@link Bamboo}(节点)的ID.
     *
     * @return my id
     */
    String getMyId();

    /**
     * 设置当前{@link Bamboo}(节点)的ID.
     *
     * @param myId my id
     */
    void setMyId(String myId);

    /**
     * 获取所有节点,排除掉自己.
     *
     * @return 排除掉自己后的所有节点
     */
    List<BambooLocator> getBambooLocatorsListExclusiveSelf();

    /**
     * 获取my id对应的{@link BambooLocator}.
     *
     * @return my self
     */
    BambooLocator getMySelf();

    /**
     * 间隔多久发送一次心跳.该时间应该小于{@link #getLeaderElectionTimeoutRangeStartInclusiveMillis()},不需要设置,
     * 根据{@link #getLeaderElectionTimeoutRangeStartInclusiveMillis()}计算即可.
     *
     * @return 发送心跳的间隔时间
     */
    long getHeartbeatTimeout();

}
