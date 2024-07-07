package com.inet.framework.servers.contracts.lambdas;

import com.inet.framework.servers.ServerRequest;
import com.inet.framework.servers.ServerResponse;

public interface ResponseActionLambda {
    /**
     * Action on request
     * 
     * @param request
     * @param response
     */
    public Boolean execute(ServerRequest request, ServerResponse response);
}
