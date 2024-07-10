package com.framework.server.router;

import java.util.ArrayList;
import java.util.List;

import com.framework.server.Middleware;
import com.framework.server.contracts.RouterInterface;
import com.framework.server.contracts.ServerInterface;
import com.framework.server.contracts.lambdas.ResponseActionLambda;
import com.framework.utils.Reflect;

public abstract class Router implements RouterInterface {
    public ServerInterface server = null;
    public final List<Middleware> middlewares = new ArrayList<>();

    public void setServer(ServerInterface server) {
        this.server = server;
    }

    public void middleware(Class<? extends Middleware> middleware) {
        Middleware instance = Reflect.newInstance(middleware, null);
        middlewares.add(instance);
    }

    public void get(String uri, ResponseActionLambda action) {
        Route route = new Route("GET", uri, action);
        route.middlewares.addAll(middlewares);
        server.getServerRoutes().add(route);
    }

    public void post(String uri, ResponseActionLambda action) {
        Route route = new Route("POST", uri, action);
        route.middlewares.addAll(middlewares);
        server.getServerRoutes().add(route);
    }

    public void put(String uri, ResponseActionLambda action) {
        Route route = new Route("PUT", uri, action);
        route.middlewares.addAll(middlewares);
        server.getServerRoutes().add(route);
    }

    public void delete(String uri, ResponseActionLambda action) {
        Route route = new Route("DELETE", uri, action);
        route.middlewares.addAll(middlewares);
        server.getServerRoutes().add(route);
    }

    public void patch(String uri, ResponseActionLambda action) {
        Route route = new Route("PATCH", uri, action);
        route.middlewares.addAll(middlewares);
        server.getServerRoutes().add(route);
    }


    public void registers() {
    }

    public void middlewares_registers() {
    }
}
