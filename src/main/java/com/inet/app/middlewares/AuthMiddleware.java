package com.inet.app.middlewares;

import com.framework.server.Middleware;
import com.framework.server.ServerRequest;
import com.framework.server.response.ServerResponse;
import com.framework.server.response.modules.Response;

public class AuthMiddleware extends Middleware {

    public Response handle(ServerRequest request, ServerResponse response) {
        return next();
    }

}
