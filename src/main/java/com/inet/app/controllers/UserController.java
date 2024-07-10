package com.inet.app.controllers;

import com.framework.constants.server.response.modules.JsonResponse;
import com.inet.app.dtos.user.CreateUserDTO;
import com.inet.app.dtos.user.UpdateUserDTO;
import com.framework.constants.server.ServerRequest;
import com.framework.constants.server.response.ServerResponse;
import com.inet.app.models.User;
import com.inet.app.models.data.UserData;
import com.inet.app.resources.error.NotFoundResource;
import com.inet.app.resources.user.CreateUserResource;
import com.inet.app.resources.user.ShowUserResource;
import com.inet.app.resources.user.UpdateUserResource;

public class UserController {
    private static JsonResponse responseUserNotFound(ServerResponse response, Integer id) {
        return response.json(new NotFoundResource("user " + id + " not found"), 404);
    }

    public static JsonResponse index(ServerRequest request, ServerResponse response) {
        User user = new User();
        var users = user.all();

        return response.json(users, 200);
    }

    public static JsonResponse show(ServerRequest request, ServerResponse response) {
        Integer id = request.getParams().getInt("id");

        User user = new User();

        UserData userData = user.find("id", id);

        if (userData == null) {
            return responseUserNotFound(response, id);
        }

        return response.json(new ShowUserResource(userData), 200);
    }

    public static JsonResponse store(ServerRequest request, ServerResponse response) {
        CreateUserDTO data = request.jsonBody(CreateUserDTO.class);

        User user = new User();

        UserData newUser = user.create(data);

        return response.json(new CreateUserResource(newUser), 201);
    }

    public static JsonResponse destroy(ServerRequest request, ServerResponse response) {
        Integer id = request.getParams().getInt("id");

        User user = new User();

        UserData userData = user.find("id", id);

        if (userData == null) {
            return responseUserNotFound(response, id);
        }

        user.delete("id", id);

        return response.json(null, 204);
    }

    public static JsonResponse update(ServerRequest request, ServerResponse response) {
        Integer id = request.getParams().getInt("id");

        User user = new User();

        UserData userData = user.find("id", id);

        if (userData == null) {
            return responseUserNotFound(response, id);
        }

        UpdateUserDTO data = request.jsonBody(UpdateUserDTO.class);

        UserData updatedUser = user.update("id", id, data);

        return response.json(new UpdateUserResource(updatedUser), 200);
    }

}
