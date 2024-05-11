package com.inet.framework.servers.contracts;

import com.inet.framework.servers.contracts.lambdas.ResponseActionLambda;
import com.inet.framework.servers.enums.MethodEnum;

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
    public String getEndPoint();

    /**
     * Get route key indentify
     * 
     * @return
     */
    public String getRouteKey();

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
}
