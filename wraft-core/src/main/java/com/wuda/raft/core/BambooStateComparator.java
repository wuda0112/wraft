package com.wuda.raft.core;

import java.util.Comparator;

/**
 * 用于比较两个节点谁更up-to-date.
 *
 * @author wuda
 */
public class BambooStateComparator implements Comparator<BambooStateComparator.Condition> {

    /**
     * 默认实例.
     */
    public final static BambooStateComparator defaultComparator = new BambooStateComparator();

    @Override
    public int compare(Condition o1, Condition o2) {
        if (o1.term < o2.term) {
            return -1;
        } else if (o1.term == o2.term) {
            return 0;
        }
        return 1;
    }

    /**
     * 比较使用的条件.
     *
     * @author wuda
     */
    public static class Condition {

        private long term;

        /**
         * 构造条件.
         *
         * @param term term
         */
        public Condition(long term) {
            this.term = term;
        }

        /**
         * 获取term
         *
         * @return term
         */
        public long getTerm() {
            return term;
        }
    }
}
