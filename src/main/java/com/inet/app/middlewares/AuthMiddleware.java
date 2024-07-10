package com.inet.app.middlewares;

import com.framework.constants.server.Middleware;
import com.framework.constants.server.ServerRequest;
import com.framework.constants.server.response.ServerResponse;
import com.framework.constants.server.response.modules.Response;

public class AuthMiddleware extends Middleware {

    public Response handle(ServerRequest request, ServerResponse response) {
        return next();
    }

}
