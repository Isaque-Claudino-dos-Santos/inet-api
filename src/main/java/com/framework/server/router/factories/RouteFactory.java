package com.framework.server.router.factories;

import com.framework.server.contracts.lambdas.ResponseActionLambda;
import com.framework.server.router.Route;
import com.framework.server.router.Router;

public class RouteFactory {
    private final Router router;

    public RouteFactory(Router router) {
        this.router = router;
    }

    public void get(String uri, ResponseActionLambda action) {
        Route route = new Route("GET", uri, action);
        route.middlewares.addAll(router.middlewares);
        router.server.getServerRoutes().add(route);
    }

    public void post(String uri, ResponseActionLambda action) {
        Route route = new Route("POST", uri, action);
        route.middlewares.addAll(router.middlewares);
        router.server.getServerRoutes().add(route);
    }

    public void put(String uri, ResponseActionLambda action) {
        Route route = new Route("PUT", uri, action);
        route.middlewares.addAll(router.middlewares);
        router.server.getServerRoutes().add(route);
    }

    public void delete(String uri, ResponseActionLambda action) {
        Route route = new Route("DELETE", uri, action);
        route.middlewares.addAll(router.middlewares);
        router.server.getServerRoutes().add(route);
    }

    public void patch(String uri, ResponseActionLambda action) {
        Route route = new Route("PATCH", uri, action);
        route.middlewares.addAll(router.middlewares);
        router.server.getServerRoutes().add(route);
    }
}
