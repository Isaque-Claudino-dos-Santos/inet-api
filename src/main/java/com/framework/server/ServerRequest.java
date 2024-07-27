package com.framework.server;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.framework.server.contracts.ServerRequestInterface;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

public class ServerRequest implements ServerRequestInterface {
    private final HttpExchange httpExchange;
    private final Headers headers;
    private final URI uri;
    private final ServerRequestParams params = new ServerRequestParams();
    private final Pattern PATTERN_ROUTE_PARAM = Pattern.compile("\\{\\w*}", Pattern.MULTILINE);

    public ServerRequest(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
        uri = httpExchange.getRequestURI();
        headers = httpExchange.getRequestHeaders();
    }

    private Boolean isParam(String part) {
        return PATTERN_ROUTE_PARAM.matcher(part).matches();
    }

    public void makeParams(String routeUri) {
        String[] requestParts = getUri().split("/");
        String[] routeParts = routeUri.split("/");

        for (int i = 0; i < routeParts.length; i++) {
            String routePart = routeParts[i];
            String requestPart = requestParts[i];

            if (isParam(routePart)) {
                String param = routePart.replace("{", "").replace("}", "");
                params.put(param, requestPart);
            }
        }
    }

    public Headers getHeaders() {
        return headers;
    }

    public ServerRequestParams getParams() {
        return params;
    }

    public String getUri() {
        return uri.getPath();
    }

    public Map<String, String> getQueries() {
        Map<String, String> queries = new HashMap<>();

        for (String query : uri.getQuery().split("&")) {
            String[] keyAndValue = query.split("=");
            queries.put(keyAndValue[0], keyAndValue[1]);
        }

        return queries;
    }

    public String getMethod() {
        return httpExchange.getRequestMethod();
    }

    public <T> T jsonBody(Class<? extends T> dto) {
        Gson gson = new Gson();
        StringBuilder body = new StringBuilder();
        Scanner input = new Scanner(httpExchange.getRequestBody());

        while (input.hasNextLine()) {
            body.append(input.nextLine());
        }

        input.close();

        return gson.fromJson(body.toString(), dto);
    }
}
