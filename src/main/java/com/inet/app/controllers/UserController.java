package com.inet.app.controllers;

import com.framework.server.enums.StatusEnum;
import com.framework.server.response.modules.JsonResponse;
import com.framework.server.ServerRequest;
import com.framework.server.response.ServerResponse;
import com.inet.app.models.User;
import com.inet.app.models.data.UserData;


public class UserController {

    public static JsonResponse index(ServerRequest request, ServerResponse response) {
        return response.json("TO IMPLEMENTATION", 500);
    }

    public static JsonResponse show(ServerRequest request, ServerResponse response) {
        Integer userId = request.params.getInt("id");
        User user = new User();

        var data = user.statement().select().getById(userId, UserData.class);

        return response.json(data, StatusEnum.INTERNAL_ERROR);
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
