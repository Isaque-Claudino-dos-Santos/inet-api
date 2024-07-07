package com.framework.server;

import java.util.ArrayList;
import java.util.List;

import com.framework.server.contracts.RouterInterface;
import com.framework.server.contracts.ServerInterface;
import com.framework.server.contracts.lambdas.ResponseActionLambda;
import com.framework.utils.Reflect;

public class Router implements RouterInterface {
    private ServerInterface server = null;
    private final List<Middleware> middlewares = new ArrayList<>();

    public void setServer(ServerInterface server) {
        this.server = server;
    }

    public void route(String method, String uri, ResponseActionLambda action) {
        Route route = new Route(method, uri, action);
        route.middlewares.addAll(middlewares);
        server.getServerRoutes().add(route);
    }

    public void middleware(Class<? extends Middleware> middleware) {
        Middleware instance = Reflect.newInstance(middleware, null);
        middlewares.add(instance);
    }

    public void registers() {
    };

    public void middlewares_registers() {
    }
}
