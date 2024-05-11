package com.inet.framework.servers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.inet.framework.servers.contracts.ServerRoutesInterface;
import com.sun.net.httpserver.HttpExchange;

public class ServerRoutes implements ServerRoutesInterface {
    private final HashMap<String, Route> routes = new HashMap<>();

    public Map<String, Route> getRoutes() {
        return routes;
    }

    @Override
    public void add(Route route) {
        routes.put(route.getRouteKey(), route);
    }

    public Boolean hasRoute(String key) {
        return routes.size() > 0 && routes.containsKey(key);
    }

    public Boolean NotHasRoute(String key) {
        return !hasRoute(key);
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        ServerRequest request = new ServerRequest(httpExchange);
        ServerResponse response = new ServerResponse(httpExchange);

        String routeKey = Route.makeRouteKey(request.getUri(), request.getMethod());

        if (NotHasRoute(routeKey)) {
            response.setStatus(404).json("Not found 404");
            return;
        }

        Route route = routes.get(routeKey);
        RouteMiddlewares middlewares = route.getMiddlewares();

        if (response.getWasAnswered()) {
            return;
        }

        while (middlewares.hasNext()) {
            Middleware middleware = middlewares.next();
            middleware.handle(request, response);

            if (response.getWasAnswered()) {
                return;
            }

            if (middleware.notShouldGoNext()) {
                break;
            }
        }

        route.getResponseAction().execute(request, response);

        if (!response.getWasAnswered()) {
            response.noContent();
        }
    }

}
