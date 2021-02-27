package com.wuda.raft.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 维护{@link Bamboo}内部状态.
 *
 * @author wuda
 */
public class BambooState {


    private String topic = "【BambooState】";

    private static Logger logger = LoggerFactory.getLogger(BambooState.class);

    private AtomicReference<BambooRole> role = new AtomicReference<>(BambooRole.FOLLOWER);

    /**
     * term.
     */
    private AtomicLong term = new AtomicLong(0);

    /**
     * candidateId that received vote in current term (or null if none)
     */
    private String votedFor;

    /**
     * 获取{@link Bamboo}当前的角色.
     *
     * @return 角色
     */
    public BambooRole getRole() {
        return role.get();
    }

    /**
     * 设置{@link Bamboo}当前的角色.
     *
     * @param role 角色
     * @return <code>true</code>-如果成功设置
     */
    public boolean setRole(BambooRole role) {
        BambooRole original;
        if (!(original = this.role.get()).equals(role)) {
            if (this.role.compareAndSet(original, role)) {
                logger.info("set role = {}", role);
                if (this.getRole().equals(BambooRole.LEADER)) {
                    Global.getLeaderElectionTimer().stop();
                    Global.getHeartbeatTimer().reset(getTerm());
                } else if (this.getRole().equals(BambooRole.FOLLOWER)) {
                    Global.getLeaderElectionTimer().reset(getTerm());
                    Global.getHeartbeatTimer().stop();
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 获取term.
     *
     * @return term
     */
    public long getTerm() {
        return term.get();
    }

    /**
     * term加一.
     */
    public void incrementTerm() {
        term.incrementAndGet();
    }

    /**
     * 尝试设置term,当且仅当给定的term比当前的值大,才会改变当前term.
     *
     * @param term term
     * @return <code>true</code>-如果定的term比当前的值大,并且成功设置
     */
    public boolean compareAndSetTermIfGreater(long term) {
        long currentTerm;
        while ((currentTerm = this.term.get()) < term) {
            if (this.term.compareAndSet(currentTerm, term)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 在当前term,把票投给了哪个节点 (or null if none)
     *
     * @return vote for
     */
    public String getVotedFor() {
        return votedFor;
    }

    /**
     * 设置vote for.
     *
     * @param votedFor 在当前term,把票投给了哪个节点 (or null if none)
     */
    public void setVotedFor(String votedFor) {
        this.votedFor = votedFor;
    }

    /**
     * 尝试成为Candidate,如果成功转为Candidate,则立即为自己投票.
     *
     * @param electionTimeoutTerm 发生election timeout时的term.比如在term=5的时候election timeout,正常情况下,成为Candidate时term应该等于6,
     *                            但是当尝试变为Candidate时,发现当前term已经变成了8,则此时不能变成Candidate,
     *                            因为从election timeout到变成Candidate这个期间(虽然时间很短,但是有可能发生),
     *                            其他节点已经更新了自己的term为8
     * @return 如果成功变成Candidate, 则返回更新后的快照; <code>null</code>-如果没有变成Candidate
     */
    public BambooStatePortionSnapshot tryBecomeCandidate(long electionTimeoutTerm) {
        if (this.term.compareAndSet(electionTimeoutTerm, electionTimeoutTerm + 1)) {
            setRole(BambooRole.CANDIDATE);
            setVotedFor(Global.getConfiguration().getMyId());
            return new BambooStatePortionSnapshot(getTerm(), getVotedFor(), getRole());
        }
        return null;
    }

    /**
     * 如果给定的参数表明其他节点比自己更up-to-date则执行更新.
     *
     * @param othersTerm 其他节点的term
     * @return 如果成功update, 则返回更新后的快照; <code>null</code>-如果没有update
     */
    public BambooStatePortionSnapshot tryUpdateIfOthersMoreUpToDate(long othersTerm, BambooRole role, String votedFor) {
        int result = BambooStateComparator.defaultComparator.compare(new BambooStateComparator.Condition(getTerm()), new BambooStateComparator.Condition(othersTerm));
        if (result < 0) {
            compareAndSetTermIfGreater(othersTerm);
            if (role != null) {
                setRole(role);
            }
            if (votedFor != null) {
                setVotedFor(votedFor);
            }
            return new BambooStatePortionSnapshot(getTerm(), getVotedFor(), getRole());
        }
        return null;
    }

    /**
     * 其他节点是否更up-to-date.
     *
     * @param othersTerm 其他节点的term
     * @return <code>true</code>-如果其他节点更up-to-date
     */
    public boolean othersMoreUpToDate(int othersTerm) {
        return BambooStateComparator.defaultComparator
                .compare(new BambooStateComparator.Condition(getTerm()), new BambooStateComparator.Condition(othersTerm)) < 0;
    }
}
