package com.inet.framework.servers.contracts;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public interface ServerConnectInterface {
    /**
     * Get server port
     * @return
     */
    public Integer getPort();

    /**
     * Get server host
     * @return
     */
    public String getHost();

    /**
     * Get server address
     * @return
     */
    public InetSocketAddress getAddress();

    /**
     * Start http server
     */
    public void start();

    /**
     * Get http server
     * @return
     */
    public HttpServer getServer();
}
