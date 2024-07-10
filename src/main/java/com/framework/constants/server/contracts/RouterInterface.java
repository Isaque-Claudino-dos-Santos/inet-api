package com.framework.constants.server.contracts;

import com.framework.constants.server.Middleware;

public interface RouterInterface {
    /**
     * Set server
     */
    public void setServer(ServerInterface server);

    /**
     * Method to override -
     * this method to register routes
     */
    public void registers();

    /**
     * Method to override -
     * this method to register middlewares to all routes registered
     */
    public void middlewares_registers();

    /**
     * Add new middleware in all routes
     */
    public void middleware(Class<? extends Middleware> middleware);
}
