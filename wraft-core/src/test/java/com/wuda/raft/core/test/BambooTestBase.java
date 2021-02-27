package com.wuda.raft.core.test;

import com.wuda.raft.core.BambooLocator;

import java.util.ArrayList;
import java.util.List;

public class BambooTestBase {

    public static List<BambooLocator> getBambooLocators() {
        List<BambooLocator> bambooLocators = new ArrayList<>(5);
        BambooLocator bambooLocator_1 = new BambooLocator("1", "localhost", 8081);
        bambooLocators.add(bambooLocator_1);
        BambooLocator bambooLocator_2 = new BambooLocator("2", "localhost", 8082);
        bambooLocators.add(bambooLocator_2);
        BambooLocator bambooLocator_3 = new BambooLocator("3", "localhost", 8083);
        bambooLocators.add(bambooLocator_3);
        BambooLocator bambooLocator_4 = new BambooLocator("4", "localhost", 8084);
        bambooLocators.add(bambooLocator_4);
        BambooLocator bambooLocator_5 = new BambooLocator("5", "localhost", 8085);
        bambooLocators.add(bambooLocator_5);

        return bambooLocators;
    }
}
