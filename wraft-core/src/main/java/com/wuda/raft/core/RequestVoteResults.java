package com.wuda.raft.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Candidate向集群中的其他机器发送投票请求,然后把每台机器返回的投票结果汇总得到的最终结果.
 *
 * @author wuda
 */
public class RequestVoteResults {
    /**
     * 这些投票结果属于哪个term.
     */
    private long term;
    /**
     * 集群中<strong>除了Candidate自己外的其他所有</strong>机器返回的领导选举投票结果.
     */
    private List<RequestVoteResult> othersVoteResults;

    /**
     * 构造实例.
     *
     * @param term 这些投票结果属于哪个term.
     */
    public RequestVoteResults(long term) {
        this.term = term;
        this.othersVoteResults = new ArrayList<>(Global.bambooTotal());
    }

    /**
     * 清理掉所有的持有的投票结果.
     */
    public void reset(long term) {
        this.term = term;
        othersVoteResults.clear();
    }

    /**
     * 是否赢得这次选举.
     *
     * @param currentTerm 当前所处的term
     * @return 选举结果的解释
     */
    public Explain explain(long currentTerm) {
        if (this.term < currentTerm) {
            return new Explain(false, "当前状态比发起投票时更up-to-date");
        }
        if (othersVoteResults.isEmpty()) {
            return new Explain(false, "还没有收到其他节点的投票");
        }
        // 给自己投的一票
        int voteGrantedCount = 1;
        for (RequestVoteResult othersVoteResult : othersVoteResults) {
            if (BambooStateComparator.defaultComparator.compare(new BambooStateComparator.Condition(currentTerm), new BambooStateComparator.Condition(othersVoteResult.getTerm())) < 0) {
                return new Explain(false, "集群中的其他节点比当前Candidate更up-to-date");
            }
            if (othersVoteResult.isVoteGranted()) {
                voteGrantedCount++;
                if (voteGrantedCount >= Utils.halfPlusOne(Global.bambooTotal())) {
                    return new Explain(true, "已经获得多数选票");
                }
            }
        }
        return new Explain(false, "没有获得多数选票");
    }

    /**
     * 添加投票回复结果.
     *
     * @param voteResult 节点的投票结果
     */
    public void add(RequestVoteResult voteResult) {
        othersVoteResults.add(voteResult);
    }

    /**
     * 对于投票结果的说明.
     *
     * @author wuda
     */
    public static class Explain {
        /**
         * 是否赢得选举.
         */
        private boolean win;
        /**
         * 描述信息
         */
        private String message;

        /**
         * 构造实例.
         *
         * @param win     {@link #win}
         * @param message {@link #message}
         */
        public Explain(boolean win, String message) {
            this.win = win;
            this.message = message;
        }

        /**
         * 是否赢得选举.
         *
         * @return {@link #win}
         */
        public boolean isWin() {
            return win;
        }

        /**
         * {@link #message}
         *
         * @return {@link #message}
         */
        public String getMessage() {
            return message;
        }
    }

}
