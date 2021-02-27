package com.wuda.raft.core;

/**
 * 请求投票的结果.
 *
 * @author wuda
 */
public class RequestVoteResult {

    /***
     * 回复投票的那台机器的id
     */
    private String bambooId;
    /**
     * 回复投票的那台机器的term,如果比当前Candidate的term还要大,则Candidate必须使用这个term更新自己的term.
     */
    private long term;
    /**
     * true means candidate received vote.
     */
    private boolean voteGranted;

    /**
     * 构造实例.
     *
     * @param bambooId    回复投票的那台机器的id
     * @param term        回复投票的那台机器的term
     * @param voteGranted true means candidate received vote.
     */
    public RequestVoteResult(String bambooId, long term, boolean voteGranted) {
        this.bambooId = bambooId;
        this.term = term;
        this.voteGranted = voteGranted;
    }

    /**
     * 回复投票的那台机器的id
     *
     * @return id
     */
    public String getBambooId() {
        return bambooId;
    }

    /**
     * 回复投票的那台机器的term.
     *
     * @return term
     */
    public long getTerm() {
        return term;
    }

    /**
     * true means candidate received vote
     *
     * @return true means candidate received vote
     */
    public boolean isVoteGranted() {
        return voteGranted;
    }
}
