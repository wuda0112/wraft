package com.wuda.raft.core;

/**
 * {@link BambooState}的部分状态的快照.
 *
 * @author wuda
 */
public class BambooStatePortionSnapshot {

    private long term;
    private String voteFor;
    private BambooRole role;

    /**
     * 构建快照实例.
     *
     * @param term    term
     * @param voteFor vote for
     * @param role    role
     */
    public BambooStatePortionSnapshot(long term, String voteFor, BambooRole role) {
        this.term = term;
        this.voteFor = voteFor;
        this.role = role;
    }

    /**
     * get term.
     *
     * @return term
     */
    public long getTerm() {
        return term;
    }

    /**
     * get vote for
     *
     * @return vote for
     */
    public String getVoteFor() {
        return voteFor;
    }

    /**
     * get role
     *
     * @return role
     */
    public BambooRole getRole() {
        return role;
    }
}
