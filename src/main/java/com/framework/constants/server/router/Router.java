package com.framework.constants.server.router;

import java.util.ArrayList;
import java.util.List;

import com.framework.constants.server.Middleware;
import com.framework.constants.server.contracts.RouterInterface;
import com.framework.constants.server.contracts.ServerInterface;
import com.framework.constants.server.contracts.lambdas.ResponseActionLambda;
import com.framework.constants.server.router.factories.RouteFactory;
import com.framework.utils.Reflect;

public abstract class Router implements RouterInterface {
    public ServerInterface server = null;
    public final List<Middleware> middlewares = new ArrayList<>();
    public final RouteFactory route = new RouteFactory(this);

    public void setServer(ServerInterface server) {
        this.server = server;
    }

    public void middleware(Class<? extends Middleware> middleware) {
        Middleware instance = Reflect.newInstance(middleware, null);
        middlewares.add(instance);
    }

    public void registers() {
    }

    public void middlewares_registers() {
    }
}
