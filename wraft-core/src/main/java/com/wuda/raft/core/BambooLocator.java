package com.wuda.raft.core;

import java.util.Objects;

/**
 * 用于定位Raft中的其中一根Bamboo.也就是唯一定位集群中的某一个节点.
 * 单词Locator取自于URL中的"L".
 *
 * @author wuda
 */
public class BambooLocator {

    /**
     * Bamboo id.
     */
    private String id;

    /**
     * The host name to connect to.
     *
     * @serial
     */
    private String host;

    /**
     * The protocol port to connect to.
     *
     * @serial
     */
    private int port = -1;

    /**
     * 构造实例.
     *
     * @param id   the id of the host
     * @param host the name of the host
     * @param port the port number on the host
     */
    public BambooLocator(String id, String host, int port) {
        this.id = id;
        this.host = Objects.requireNonNull(host);
        this.port = port;
    }

    /**
     * get bamboo id.
     *
     * @return bamboo id
     */
    public String getId() {
        return id;
    }

    /**
     * get host.
     *
     * @return host
     */
    public String getHost() {
        return host;
    }

    /**
     * get port
     *
     * @return port
     */
    public int getPort() {
        return port;
    }
}
