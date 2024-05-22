package com.inet.routes;

import com.inet.controllers.UserController;
import com.inet.framework.servers.Router;
import com.inet.middlewares.AuthMiddleware;

public class PublicRouter extends Router {

    public void registers() {
        route("GET", "/users", UserController::index);
        route("POST", "/users", UserController::store);
        route("GET", "/users/{id}", UserController::show);
        route("PUT", "/users/{id}", UserController::update);
        route("DELETE", "/users/{id}", UserController::destroy);
    }

    public void middlewares_registers() {
        middleware(AuthMiddleware.class);
    }
}
