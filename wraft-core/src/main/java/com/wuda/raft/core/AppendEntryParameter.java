package com.wuda.raft.core;

/**
 * AppendEntries RPC 的请求参数
 *
 * @author wuda
 */
public class AppendEntryParameter {

    /**
     * leader’s term
     */
    private long term;
    /**
     * so follower can redirect clients
     */
    private String leaderId;
    /**
     * log entries to store (empty for heartbeat; may send more than one for efficiency)
     */
    private byte[] entries = new byte[0];

    /**
     * 创建heartbeat.
     *
     * @param term     leader’s term
     * @param leaderId leader’s id
     */
    public static AppendEntryParameter createHeartbeat(long term, String leaderId) {
        return new AppendEntryParameter(term, leaderId);
    }

    /**
     * 构造实例.
     *
     * @param term     leader’s term
     * @param leaderId leader’s id
     */
    public AppendEntryParameter(long term, String leaderId) {
        this.term = term;
        this.leaderId = leaderId;
    }

    /**
     * get term
     *
     * @return leader’s term
     */
    public long getTerm() {
        return term;
    }

    /**
     * get leader id
     *
     * @return leader’s id
     */
    public String getLeaderId() {
        return leaderId;
    }

    /**
     * get entry
     *
     * @return entry
     */
    public byte[] getEntries() {
        return entries;
    }

    /**
     * 是否心跳.
     *
     * @return <code>true</code>-如果是
     */
    public boolean isHeartbeat() {
        return getEntries() == null || getEntries().length == 0;
    }
}
