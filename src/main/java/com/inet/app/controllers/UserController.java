package com.inet.app.controllers;

import com.inet.app.dtos.user.CreateUserDTO;
import com.inet.app.dtos.user.UpdateUserDTO;
import com.framework.server.ServerRequest;
import com.framework.server.ServerResponse;
import com.inet.app.models.User;
import com.inet.app.models.data.UserData;
import com.inet.app.resources.error.NotFoundResource;
import com.inet.app.resources.user.CreateUserResource;
import com.inet.app.resources.user.ShowUserResource;
import com.inet.app.resources.user.UpdateUserResource;

public class UserController {
    private static Boolean responseUserNotFound(ServerResponse response, Integer id) {
        return response
                .setStatus(404)
                .json(new NotFoundResource("user " + id + " not found"));
    }

    public static Boolean index(ServerRequest request, ServerResponse response) {
        User user = new User();
        var users = user.all();

        return response.json(users);
    }

    public static Boolean show(ServerRequest request, ServerResponse response) {
        Integer id = request.getParams().getInt("id");

        User user = new User();

        UserData userData = user.find("id", id);

        if (userData == null) {
            return responseUserNotFound(response, id);
        }

        return response.json(new ShowUserResource(userData));
    }

    public static Boolean store(ServerRequest request, ServerResponse response) {
        CreateUserDTO data = request.jsonBody(CreateUserDTO.class);

        User user = new User();

        UserData newUser = user.create(data);

        return response.json(new CreateUserResource(newUser));
    }

    public static Boolean destroy(ServerRequest request, ServerResponse response) {
        Integer id = request.getParams().getInt("id");

        User user = new User();

        UserData userData = user.find("id", id);

        if (userData == null) {
            return responseUserNotFound(response, id);
        }

        user.delete("id", id);

        return response.noContent();
    }

    public static Boolean update(ServerRequest request, ServerResponse response) {
        Integer id = request.getParams().getInt("id");

        User user = new User();

        UserData userData = user.find("id", id);

        if (userData == null) {
            return responseUserNotFound(response, id);
        }

        UpdateUserDTO data = request.jsonBody(UpdateUserDTO.class);

        UserData updatedUser = user.update("id", id, data);

        return response.json(new UpdateUserResource(updatedUser));
    }

}
