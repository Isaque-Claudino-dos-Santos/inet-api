package com.framework.server.contracts;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public interface ServerInterface {

    /**
     * Get server port
     */
    public Integer getPort();

    /**
     * Get server host
     */
    public String getHost();

    /**
     * Get server address
     */
    public InetSocketAddress getAddress();

    /**
     * Start http server
     */
    public void start();

    /**
     * Get http server
     */
    public HttpServer getServer();

    /**
     * Return server routes
     */
    public ServerRoutesInterface getServerRoutes();
}