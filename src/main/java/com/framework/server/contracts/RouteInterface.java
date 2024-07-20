package com.framework.server.contracts;

import com.framework.server.contracts.lambdas.ResponseActionLambda;
import com.framework.server.enums.MethodEnum;

public interface RouteInterface {
    /**
     * Get route method
     */
    public String getMethod();

    /**
     * Get route end point
     */
    public String getUri();

    /**
     * Set action on request
     */
    public RouteInterface setResponseAction(ResponseActionLambda action);

    /**
     * get action request
     */
    public ResponseActionLambda getResponseAction();

    /**
     * Set request method
     */
    public RouteInterface setMethod(String method);

    /**
     * Set request method
     */
    public RouteInterface setMethod(MethodEnum method);

    /**
     * Get all middleware of route
     */
    public RouteMiddlewaresInterface getMiddlewares();

    /**
     * Check if route uri has param
     */
    public Boolean hasParam();

    /**
     * Get route id
     */
    public String getId();

    /**
     * Check if route id match with value
     */
    public Boolean idMatchWith(String value);
}
