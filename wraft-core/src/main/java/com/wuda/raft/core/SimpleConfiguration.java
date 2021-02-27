package com.wuda.raft.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一种实现类.
 *
 * @author wuda
 */
public class SimpleConfiguration implements Configuration {

    private long leaderElectionTimeoutRangeStartInclusiveMillis;
    private long leaderElectionTimeoutRangeEndInclusiveMillis;
    private List<BambooLocator> bambooLocators;
    private Map<String, BambooLocator> byIdBambooLocatorMap;
    private List<BambooLocator> bambooLocatorsExclusiveSelf;
    private String myId;

    @Override
    public long getLeaderElectionTimeoutRangeStartInclusiveMillis() {
        return leaderElectionTimeoutRangeStartInclusiveMillis;
    }

    @Override
    public void setLeaderElectionTimeoutRangeStartInclusiveMillis(long leaderElectionTimeoutRangeStartInclusiveMillis) {
        this.leaderElectionTimeoutRangeStartInclusiveMillis = leaderElectionTimeoutRangeStartInclusiveMillis <= 0 ? 150 : leaderElectionTimeoutRangeStartInclusiveMillis;
    }

    @Override
    public long getLeaderElectionTimeoutRangeEndInclusiveMillis() {
        return leaderElectionTimeoutRangeEndInclusiveMillis;
    }

    @Override
    public void setLeaderElectionTimeoutRangeEndInclusiveMillis(long leaderElectionTimeoutRangeEndInclusiveMillis) {
        this.leaderElectionTimeoutRangeEndInclusiveMillis = leaderElectionTimeoutRangeEndInclusiveMillis <= 0 ? 300 : leaderElectionTimeoutRangeEndInclusiveMillis;
    }

    @Override
    public List<BambooLocator> getBambooLocators() {
        return bambooLocators;
    }

    @Override
    public void setBambooLocators(List<BambooLocator> bambooLocators) {
        this.bambooLocators = bambooLocators;
        this.byIdBambooLocatorMap = new HashMap<>(bambooLocators.size());
        this.bambooLocatorsExclusiveSelf = new ArrayList<>(bambooLocators.size());
        for (BambooLocator bambooLocator : bambooLocators) {
            this.byIdBambooLocatorMap.put(bambooLocator.getId(), bambooLocator);
            if (!bambooLocator.getId().equals(myId)) {
                this.bambooLocatorsExclusiveSelf.add(bambooLocator);
            }
        }
    }

    @Override
    public String getMyId() {
        return myId;
    }

    @Override
    public void setMyId(String myId) {
        this.myId = myId;
    }

    @Override
    public List<BambooLocator> getBambooLocatorsListExclusiveSelf() {
        return this.bambooLocatorsExclusiveSelf;
    }

    @Override
    public BambooLocator getMySelf() {
        return byIdBambooLocatorMap.get(getMyId());
    }

    @Override
    public long getHeartbeatTimeout() {
        return Utils.multiply(getLeaderElectionTimeoutRangeStartInclusiveMillis(), 0.85);
    }
}
