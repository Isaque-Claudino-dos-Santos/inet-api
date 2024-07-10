package com.framework.server.router;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.framework.server.Middleware;
import com.framework.server.contracts.RouterInterface;
import com.framework.server.contracts.ServerInterface;
import com.framework.server.contracts.lambdas.ResponseActionLambda;
import com.framework.utils.Reflect;

public class Router implements RouterInterface {
    private ServerInterface server = null;
    public final List<Middleware> middlewares = new ArrayList<>();

    public void setServer(ServerInterface server) {
        this.server = server;
    }

    @SafeVarargs
    public final void middleware(Class<? extends Middleware>... classMiddlewares) {
        for (Class<? extends Middleware> classMiddleware : classMiddlewares) {
            Middleware middleware = Reflect.newInstance(classMiddleware, null);
            middlewares.add(middleware);
        }
    }

    public Router group(Consumer<Router> consumer) {
        Router router = new Router();
        router.setServer(server);
        consumer.accept(router);
        return router;
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
