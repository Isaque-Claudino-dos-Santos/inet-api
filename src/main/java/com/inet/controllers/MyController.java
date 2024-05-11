package com.inet.controllers;

import com.inet.dtos.IndexMyDto;
import com.inet.framework.servers.ServerRequest;
import com.inet.framework.servers.ServerResponse;

public class MyController {
    static class Data {
        public String name = "John Doe";
        public String email = "johndoe@gmail.com";
    }

    public static Boolean index(ServerRequest request, ServerResponse response) {
        var body = request.jsonBody(IndexMyDto.class);

        return response.json(body);
    }
}
