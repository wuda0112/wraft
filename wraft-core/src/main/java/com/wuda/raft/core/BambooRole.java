package com.wuda.raft.core;

import com.wuda.raft.fsm.State;

/**
 * state space.
 *
 * @author wuda
 */
public enum BambooRole implements State {

    /**
     * follower.
     */
    FOLLOWER("follower", "follower"),
    /**
     * candidate.
     */
    CANDIDATE("candidate", "candidate"),
    /**
     * leader.
     */
    LEADER("leader", "leader");

    private String name;
    private String description;

    /**
     * 构建实例.
     *
     * @param name        name
     * @param description description
     */
    BambooRole(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
