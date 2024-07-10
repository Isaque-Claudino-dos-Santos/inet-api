package com.inet.app.routes;

import com.inet.app.controllers.UserController;
import com.framework.server.Router;
import com.inet.app.middlewares.AuthMiddleware;

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
