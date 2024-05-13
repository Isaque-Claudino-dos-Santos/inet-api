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
        var id = request.getParams().getInt("id");
        var test_id = request.getParams().getInt("test_id");

        System.out.println("id: " + id);
        System.out.println("test_id: " + test_id);

        return response.json(body);
    }
}
