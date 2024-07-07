package com.framework.servers.contracts;

import com.framework.servers.contracts.lambdas.ResponseActionLambda;
import com.framework.servers.enums.MethodEnum;

public interface RouteInterface {
    /**
     * Get route method
     * 
     * @return
     */
    public String getMethod();

    /**
     * Get route end point
     * 
     * @return
     */
    public String getUri();

    /**
     * Set action on request
     */
    public RouteInterface setResponseAction(ResponseActionLambda action);

    /**
     * get action request
     * 
     * @return
     */
    public ResponseActionLambda getResponseAction();

    /**
     * Set request method
     * 
     * @param method
     */
    public RouteInterface setMethod(String method);

    /**
     * Set request method
     * 
     * @param method
     */
    public RouteInterface setMethod(MethodEnum method);

    /**
     * Get all middleware of route
     * 
     * @return
     */
    public RouteMiddlewaresInterface getMiddlewares();

    /**
     * Check if route uri has param
     * 
     * @return
     */
    public Boolean hasParam();

    /**
     * Get route id
     * 
     * @return
     */
    public String getId();

    /**
     * Check if route id match with value
     * @param value
     * @return
     */
    public Boolean idMatchWith(String value);
}
