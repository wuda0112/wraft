package com.wuda.raft.core;

/**
 * AppendEntries RPC result.
 *
 * @author wuda
 */
public class AppendEntryResult {

    /**
     * currentTerm, for leader to update itself.
     * 回复rpc请求的那台机器的term.
     */
    private long term;
    /**
     * true if follower contained entry matching prevLogIndex and prevLogTerm
     */
    private Boolean success;

    /**
     * 构建实例.
     *
     * @param term    回复rpc请求的那台机器的term.
     * @param success is success
     */
    public AppendEntryResult(long term, Boolean success) {
        this.term = term;
        this.success = success;
    }

    /**
     * get term.
     *
     * @return 回复rpc请求的那台机器的term
     */
    public long getTerm() {
        return term;
    }

    /**
     * is success
     *
     * @return success
     */
    public Boolean getSuccess() {
        return success;
    }
}
