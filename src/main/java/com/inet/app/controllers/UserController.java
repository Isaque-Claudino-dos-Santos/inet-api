package com.inet.app.controllers;

import com.framework.server.annotations.Middleware;
import com.framework.server.annotations.Route;
import com.framework.server.response.modules.JsonResponse;
import com.framework.server.ServerRequest;
import com.framework.server.response.ServerResponse;
import com.inet.app.middlewares.AuthMiddleware;
import com.inet.app.models.User;
import com.inet.app.models.data.UserData;


@Middleware(AuthMiddleware.class)
public class UserController {

    @Route(method = "GET", uri = "/")
    @Middleware(AuthMiddleware.class)
    public static JsonResponse index(ServerRequest request, ServerResponse response) {
        return response.json("TO IMPLEMENTATION", 500);
    }

    public static JsonResponse show(ServerRequest request, ServerResponse response) {
        Integer id = request.getParams().getInt("id");
        User user = new User();

        var data = user
                .statement()
                .select()
                .getById(id, UserData.class);

        return response.json(data, 200);
    }

    public static JsonResponse store(ServerRequest request, ServerResponse response) {
        return response.json("TO IMPLEMENTATION", 500);

    }

    public static JsonResponse destroy(ServerRequest request, ServerResponse response) {
        return response.json("TO IMPLEMENTATION", 500);

    }

    public static JsonResponse update(ServerRequest request, ServerResponse response) {
        return response.json("TO IMPLEMENTATION", 500);
    }

}
