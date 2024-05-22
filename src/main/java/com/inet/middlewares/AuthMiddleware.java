package com.inet.middlewares;

import com.inet.framework.servers.Middleware;
import com.inet.framework.servers.ServerRequest;
import com.inet.framework.servers.ServerResponse;

public class AuthMiddleware extends Middleware {

    public Boolean handle(ServerRequest request, ServerResponse response) {
        return next();
    }

}
