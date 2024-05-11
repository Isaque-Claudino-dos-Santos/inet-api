package com.inet.routes;

import com.inet.controllers.MyController;
import com.inet.framework.servers.Router;
import com.inet.middlewares.AuthMiddleware;

public class PublicRouter extends Router {

    public void registers() {
        route("GET", "/", MyController::index);
    }

    public void middlewares_registers() {
        middleware(AuthMiddleware.class);
    }
}
