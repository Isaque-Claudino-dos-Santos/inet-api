package com.inet.framework.servers.contracts;

public interface ServerInterface {
    /**
     * Get http connect
     * 
     * @return
     */
    public ServerConnectInterface getConnect();

    /**
     * Start http server
     */
    public void start();

    /**
     * Return server routes
     * 
     * @return
     */
    public ServerRoutesInterface getServerRoutes();
}