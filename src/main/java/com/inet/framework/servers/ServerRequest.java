package com.inet.framework.servers;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.inet.framework.servers.contracts.ServerHeadersInterface;
import com.inet.framework.servers.contracts.ServerRequestInterface;
import com.sun.net.httpserver.HttpExchange;

public class ServerRequest implements ServerRequestInterface {
    private final HttpExchange httpExchange;
    private final ServerHeaders headers;
    private final URI uri;

    public ServerRequest(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
        uri = httpExchange.getRequestURI();
        headers = new ServerHeaders(httpExchange.getRequestHeaders());
    }

    public String getUri() {
        return uri.getPath();
    }

    public Map<String, String> getQuerys() {
        Map<String, String> querys = new HashMap<>();
        List<String> params = Arrays.asList(uri.getQuery().split("&"));

        params.forEach((param) -> {
            String[] query = param.split("=");
            querys.put(query[0], query[1]);
        });

        return querys;
    }

    public ServerHeadersInterface getHeaders() {
        return headers;
    }

    public String getMethod() {
        return httpExchange.getRequestMethod();
    }

    public <T extends Object> T jsonBody(Class<? extends T> dto) {
        Gson gson = new Gson();
        String body = "";
        Scanner input = new Scanner(httpExchange.getRequestBody());

        while (input.hasNextLine()) {
            body += input.nextLine();
        }

        input.close();

        return gson.fromJson(body, dto);
    }
}
