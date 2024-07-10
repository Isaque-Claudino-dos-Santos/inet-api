package com.framework.server.contracts;

import com.framework.server.response.modules.Response;
import com.framework.server.ServerRequest;
import com.framework.server.response.ServerResponse;

public interface MiddlewareInterface {
    public Response handle(ServerRequest request, ServerResponse response);

    public Response next();
}
