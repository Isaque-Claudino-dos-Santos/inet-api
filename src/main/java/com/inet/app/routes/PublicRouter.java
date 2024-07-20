package com.inet.app.routes;

import com.inet.app.controllers.UserController;
import com.framework.server.router.Router;
import com.inet.app.middlewares.AuthMiddleware;

public class PublicRouter extends Router {

    public void registers() {
        get("/users", UserController::index);
        post("/users", UserController::store);
        get("/users/{id}", UserController::show);
        put("/users/{id}", UserController::update);
        delete("/users/{id}", UserController::destroy);
    }

    public void middlewares_registers() {
        middleware(AuthMiddleware.class);
    }
}
