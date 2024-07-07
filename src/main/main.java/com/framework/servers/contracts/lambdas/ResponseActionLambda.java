package com.framework.servers.contracts.lambdas;

import com.framework.servers.ServerRequest;
import com.framework.servers.ServerResponse;

public interface ResponseActionLambda {
    /**
     * Action on request
     * 
     * @param request
     * @param response
     */
    public Boolean execute(ServerRequest request, ServerResponse response);
}
