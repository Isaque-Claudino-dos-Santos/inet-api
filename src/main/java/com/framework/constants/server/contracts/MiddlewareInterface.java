package com.framework.constants.server.contracts;

import com.framework.constants.server.response.modules.Response;
import com.framework.constants.server.ServerRequest;
import com.framework.constants.server.response.ServerResponse;

public interface MiddlewareInterface {
    public Response handle(ServerRequest request, ServerResponse response);

    public Response next();
}
