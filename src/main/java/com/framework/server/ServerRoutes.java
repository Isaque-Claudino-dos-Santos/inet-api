package com.framework.server;

import java.io.IOException;
import java.util.HashMap;

import com.framework.server.contracts.ServerRoutesInterface;
import com.inet.settings.Env;
import com.sun.net.httpserver.HttpExchange;

public class ServerRoutes extends HashMap<String, Route> implements ServerRoutesInterface {
    public ServerRoutes add(Route route) {
        put(route.getId(), route);
        return this;
    }

    public Boolean hasRoute(String key) {
        return size() > 0 && containsKey(key);
    }

    public Boolean notHasRoute(String key) {
        return !hasRoute(key);
    }

    public Boolean notIsParam(String path) {
        return Env.PATTERN_ROUTE_PARAM.matcher(path).find();
    }

    private Route findRoute(String id) {
        for (Route route : values()) {
            if (route.idMatchWith(id)) {
                return route;
            }
        }
        return null;
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        ServerRequest request = new ServerRequest(httpExchange);
        ServerResponse response = new ServerResponse(httpExchange);

        if (response.getWasAnswered()) {
            return;
        }

        String routeRequestId = Route.makeRouteId(request.getUri(), request.getMethod());
        Route route = findRoute(routeRequestId);

        if (route == null) {
            response.setStatus(404).json("Not found 404");
            return;
        }

        request.makeParams(route.getUri());

        RouteMiddlewares middlewares = route.getMiddlewares();

        while (middlewares.hasNext()) {
            Middleware middleware = middlewares.next();
            middleware.handle(request, response);

            if (response.getWasAnswered()) {
                middlewares.reset();
                return;
            }

            if (middleware.notShouldGoNext()) {
                middlewares.reset();
                break;
            }
        }

        route.getResponseAction().execute(request, response);
        middlewares.reset();

        if (!response.getWasAnswered()) {
            response.noContent();
        }

    }

}
