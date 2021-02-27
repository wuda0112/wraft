package com.wuda.raft.core;

import java.util.Objects;

/**
 * 工具类.
 *
 * @author wuda
 */
public class Utils {

    /**
     * 返回给定数字的一半加一的值.比如给定数字是5,则它的一半加一是3.
     *
     * @param number the number
     * @return 当前数字的一半再加上一的值
     */
    public static int halfPlusOne(int number) {
        return (number / 2) + 1;
    }

    /**
     * 统一日志头信息.
     *
     * @param topic    日志主题
     * @param bambooId bamboo id
     * @param term     term
     * @return 日志头
     */
    public static String getLogHeader(String topic, String bambooId, Long term) {
        Objects.requireNonNull(topic);
        StringBuilder builder = new StringBuilder("【").append(topic).append("】");
        if (bambooId != null) {
            builder.append("【bamboo id = ").append(bambooId).append("】");
        }
        if (term != null) {
            builder.append("【term = ").append(term).append("】");
        }
        return builder.toString();
    }

    /**
     * long类型的数字乘以一个double类型的数字的结果.
     *
     * @param number   long类型的数字
     * @param multiply double类型的数字
     * @return 结果
     */
    public static long multiply(long number, double multiply) {
        return (long) (number * multiply);
    }

}
