package com.inet.app.routes;

import com.inet.app.controllers.UserController;
import com.framework.server.router.Router;
import com.inet.app.middlewares.AuthMiddleware;

public class PublicRouter extends Router {

    public void registers() {
        route.get("/users", UserController::index);
        route.post("/users", UserController::store);
        route.get("/users/{id}", UserController::show);
        route.put("/users/{id}", UserController::update);
        route.delete("/users/{id}", UserController::destroy);
    }

    public void middlewares_registers() {
        middleware(AuthMiddleware.class);
    }
}
