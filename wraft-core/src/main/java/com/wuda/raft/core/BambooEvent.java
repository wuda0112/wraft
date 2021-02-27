package com.wuda.raft.core;

import com.wuda.raft.fsm.Event;

/**
 * 事件.
 *
 * @author wuda
 */
public enum BambooEvent implements Event {
    /**
     * 选举超时.
     */
    ELECTION_TIMEOUT,
    /**
     * 当选为Leader.
     */
    ELECTION_WIN;
}
