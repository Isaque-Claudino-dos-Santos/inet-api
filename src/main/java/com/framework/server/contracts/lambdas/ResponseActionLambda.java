package com.framework.server.contracts.lambdas;

import com.framework.server.ServerRequest;
import com.framework.server.ServerResponse;

public interface ResponseActionLambda {
    /**
     * Action on request
     * 
     * @param request
     * @param response
     */
    public Boolean execute(ServerRequest request, ServerResponse response);
}
