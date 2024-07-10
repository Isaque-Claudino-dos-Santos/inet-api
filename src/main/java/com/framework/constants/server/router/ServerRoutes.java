package com.framework.constants.server.router;

import java.io.IOException;
import java.util.HashMap;

import com.framework.constants.server.Middleware;
import com.framework.constants.server.RouteMiddlewares;
import com.framework.constants.server.contracts.ServerRoutesInterface;
import com.framework.constants.server.response.ServerResponse;
import com.framework.constants.server.response.modules.Response;
import com.framework.constants.server.ServerRequest;
import com.sun.net.httpserver.HttpExchange;

public class ServerRoutes extends HashMap<String, Route> implements ServerRoutesInterface {
    private Boolean wasAnswered = false;

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

    private Route findRoute(String id) {
        for (Route route : values()) {
            if (route.idMatchWith(id)) {
                return route;
            }
        }
        return null;
    }

    public void handle(HttpExchange httpExchange) {
        ServerRequest request = new ServerRequest(httpExchange);
        ServerResponse response = new ServerResponse(httpExchange);

        try {

            if (wasAnswered) {
                return;
            }

            String routeRequestId = Route.makeRouteId(request.getUri(), request.getMethod());
            Route route = findRoute(routeRequestId);

            if (route == null) {
                response.json("Not found 404", 404).send();
                wasAnswered = true;
                return;
            }

            request.makeParams(route.getUri());

            RouteMiddlewares middlewares = route.getMiddlewares();

            while (middlewares.hasNext()) {
                Middleware middleware = middlewares.next();

                Response middlewareResponse = middleware.handle(request, response);

                if (middlewareResponse != null) {
                    middlewareResponse.send();
                    middlewares.reset();
                    wasAnswered = true;
                    return;
                }

                if (middleware.notShouldGoNext()) {
                    middlewares.reset();
                    break;
                }
            }

            Response routeResponse = route.getResponseAction().execute(request, response);

            if (routeResponse != null) {
                routeResponse.send();
                wasAnswered = true;
            }

            middlewares.reset();

            if (!wasAnswered) {
                response.json(null, 204).send();
                wasAnswered = true;
            }

        } catch (Exception exception) {
            if (wasAnswered) return;

            try {
                response.json(exception.getMessage(), 500).send();
                wasAnswered = true;
            } catch (IOException exception1) {
                exception1.printStackTrace();
            }
        }
    }

}
