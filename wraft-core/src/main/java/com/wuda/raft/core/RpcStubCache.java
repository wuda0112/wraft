package com.wuda.raft.core;

import com.wuda.raft.grpc.codegen.RpcServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 缓存节点的 rpc stub.
 *
 * @author wuda
 */
public class RpcStubCache {

    private static Map<BambooLocator, RpcServiceGrpc.RpcServiceBlockingStub> byBambooLocatorMap;

    public static void init(List<BambooLocator> bambooLocators) {
        byBambooLocatorMap = new HashMap<>(bambooLocators.size());
        for (BambooLocator bambooLocator : bambooLocators) {
            ManagedChannel channel = ManagedChannelBuilder
                    .forAddress(bambooLocator.getHost(), bambooLocator.getPort())
                    .usePlaintext()
                    .build();
            RpcServiceGrpc.RpcServiceBlockingStub stub = RpcServiceGrpc.newBlockingStub(channel);
            byBambooLocatorMap.put(bambooLocator, stub);
        }
    }

    public static RpcServiceGrpc.RpcServiceBlockingStub getStub(BambooLocator bambooLocator) {
        return byBambooLocatorMap.get(bambooLocator);
    }
}
