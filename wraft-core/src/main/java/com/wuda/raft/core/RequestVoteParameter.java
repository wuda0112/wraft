package com.wuda.raft.core;

/**
 * 请求投票的参数.
 *
 * @author wuda
 */
public class RequestVoteParameter {
    /**
     * candidate’s term
     */
    private long candidateTerm;
    /**
     * candidate requesting vote.
     */
    private String candidateId;

    /**
     * 构造实例.
     *
     * @param candidateTerm candidate’s term
     * @param candidateId   candidate requesting vote.
     */
    public RequestVoteParameter(long candidateTerm, String candidateId) {
        this.candidateTerm = candidateTerm;
        this.candidateId = candidateId;
    }

    /**
     * get candidate’s term
     *
     * @return candidate’s term
     */
    public long getCandidateTerm() {
        return candidateTerm;
    }

    /**
     * get candidate identifier.
     *
     * @return candidate identifier
     */
    public String getCandidateId() {
        return candidateId;
    }
}
