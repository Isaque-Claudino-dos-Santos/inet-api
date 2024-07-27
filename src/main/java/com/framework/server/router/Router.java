package com.framework.server.router;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.framework.server.Middleware;
import com.framework.server.Server;
import com.framework.server.contracts.RouterInterface;
import com.framework.server.contracts.lambdas.ResponseActionLambda;
import com.framework.utils.Reflect;

public class Router implements RouterInterface {
    private Server server = null;
    public final List<Middleware> middlewares = new ArrayList<>();

    public Router() {
    }

    public Router(Server server) {
        this.server = server;
    }

    @Override
    public void registerRouterInServer() {
        middlewares_registers();
        routes_registers();
    }

    @SafeVarargs
    public final void middleware(Class<? extends Middleware>... classMiddlewares) {
        for (Class<? extends Middleware> classMiddleware : classMiddlewares) {
            Middleware middleware = Reflect.newInstance(classMiddleware, null);
            middlewares.add(middleware);
        }
    }

    public Router group(Consumer<Router> consumer) {
        Router router = new Router(server);
        consumer.accept(router);
        return router;
    }

    @Override
    public void request(String method, String uri, ResponseActionLambda action) {
        Route route = new Route(method, uri, action);
        route.middlewares.addAll(middlewares);
        server.getServerRoutes().add(route);
    }

    public void get(String uri, ResponseActionLambda action) {
        request("GET", uri, action);
    }

    public void post(String uri, ResponseActionLambda action) {
        request("POST", uri, action);
    }

    public void put(String uri, ResponseActionLambda action) {
        request("PUT", uri, action);
    }

    public void delete(String uri, ResponseActionLambda action) {
        request("DELETE", uri, action);
    }

    public void patch(String uri, ResponseActionLambda action) {
        request("PATCH", uri, action);
    }


    public void registers() {
    }

    @Override
    public void routes_registers() {
        registers();
    }

    public void middlewares_registers() {
    }
}
