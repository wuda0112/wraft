package com.wuda.raft.core.test;

import com.wuda.raft.core.Bamboo;
import com.wuda.raft.core.Configuration;
import com.wuda.raft.core.SimpleConfiguration;

public class Bamboo1 {

    public static void main(String[] args) {

        Configuration configuration = new SimpleConfiguration();
        configuration.setMyId("1");
        configuration.setBambooLocators(BambooTestBase.getBambooLocators());
        configuration.setLeaderElectionTimeoutRangeStartInclusiveMillis(150);
        configuration.setLeaderElectionTimeoutRangeEndInclusiveMillis(300);
        Bamboo bamboo = new Bamboo(configuration);
        new Thread(bamboo::start).start();

        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
