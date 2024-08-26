package com.inet.app.controllers;

import com.framework.server.enums.StatusEnum;
import com.framework.server.annotations.Middleware;
import com.framework.server.annotations.Route;
import com.framework.server.response.modules.JsonResponse;
import com.framework.server.ServerRequest;
import com.framework.server.response.ServerResponse;
import com.framework.utils.ExceptionHandler;
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

    @Route(method = "GET", uri = "/user/{id}")
    public static JsonResponse show(ServerRequest request, ServerResponse response) {
        try {

            Integer userId = request.params.getInt("id");
            User user = new User();

            var data = user.statement().select().getById(userId, UserData.class);

            return response.json(data, StatusEnum.INTERNAL_ERROR);
        }catch (Exception exception) {
            ExceptionHandler.print(exception);
            return null;
        }
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
