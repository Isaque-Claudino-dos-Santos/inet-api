package com.inet.routes;

import com.inet.controllers.MyController;
import com.inet.framework.servers.Router;
import com.inet.middlewares.AuthMiddleware;

public class PublicRouter extends Router {

    public void registers() {
        route("GET", "/user/{id}/test/{test_id}", MyController::index);
        route("GET", "/user", MyController::index);
        route("GET", "/user/{id}", MyController::index);
    }

    public void middlewares_registers() {
        middleware(AuthMiddleware.class);
    }
}
