package com.framework.server.contracts;

import com.framework.server.Middleware;
import com.framework.server.contracts.lambdas.ResponseActionLambda;

public interface RouterInterface {
    /**
     * Register router in server
     */
    public void registerRouterInServer();

    /**
     * this method to register routes
     */
    public void registers();

    /**
     * alias from registers method
     */
    public void routes_registers();

    /**
     * this method to register middlewares to all routes registered
     */
    public void middlewares_registers();

    /**
     * Add new middleware in all routes
     */
    public void middleware(Class<? extends Middleware>... classMiddlewares);

    public void request(String method, String uri, ResponseActionLambda action);

    public void get(String uri, ResponseActionLambda action);

    public void post(String uri, ResponseActionLambda action);

    public void put(String uri, ResponseActionLambda action);

    public void delete(String uri, ResponseActionLambda action);

    public void patch(String uri, ResponseActionLambda action);
}
