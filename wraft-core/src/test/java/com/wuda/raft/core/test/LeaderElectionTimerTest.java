package com.wuda.raft.core.test;

import com.wuda.raft.core.LeaderElectionTimer;
import org.apache.commons.lang3.RandomUtils;

import java.util.Date;

public class LeaderElectionTimerTest {


    public static void main(String[] args) {
        LeaderElectionTimer timer = new LeaderElectionTimer(() -> {
            System.out.println(Thread.currentThread() + ":" + new Date() + ": task run");
        },150,300);
        timer.start(1);
        long sleep = RandomUtils.nextLong(100L, 1000L);
        while (true) {
            try {
                Thread.sleep(sleep);
                timer.reset(1);
                System.out.println("reset");
                sleep = RandomUtils.nextLong(100L, 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
