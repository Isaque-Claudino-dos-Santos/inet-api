package com.framework.constants.server.contracts.lambdas;

import com.framework.constants.server.ServerRequest;
import com.framework.constants.server.response.ServerResponse;
import com.framework.constants.server.response.modules.Response;

public interface ResponseActionLambda {
    /**
     * Action on request
     */
    public Response execute(ServerRequest request, ServerResponse response);
}
