package com.framework.server.contracts;

import com.framework.server.Middleware;
import com.framework.server.contracts.lambdas.ResponseActionLambda;
import com.framework.server.enums.MethodEnum;

public interface RouteInterface {
    /**
     * Get route method
     */
    String getMethod();

    /**
     * Get route end point
     */
    String getUri();

    /**
     * Set action on request
     */
    RouteInterface setResponseAction(ResponseActionLambda action);

    /**
     * get action request
     */
    ResponseActionLambda getResponseAction();

    /**
     * Set request method
     */
    RouteInterface setMethod(String method);

    /**
     * Set request method
     */
    RouteInterface setMethod(MethodEnum method);

    /**
     * Get all middleware of route
     */
    RouteMiddlewaresInterface getMiddlewares();

    /**
     * Check if route uri has param
     */
    Boolean hasParam();

    /**
     * Get route id
     */
    String getId();

    /**
     * Check if route id match with value
     */
    Boolean idMatchWith(String value);

    /**
     * add middleware
     */
    void addMiddleware(Class<? extends Middleware> middlewareClass);

    /**
     * add middleware
     */
    void addMiddleware(Middleware middlewareClass);
}
