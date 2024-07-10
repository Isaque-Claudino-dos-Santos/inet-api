package com.framework.server.contracts.lambdas;

import com.framework.server.ServerRequest;
import com.framework.server.response.ServerResponse;
import com.framework.server.response.modules.Response;

public interface ResponseActionLambda {
    /**
     * Action on request
     */
    public Response execute(ServerRequest request, ServerResponse response);
}
