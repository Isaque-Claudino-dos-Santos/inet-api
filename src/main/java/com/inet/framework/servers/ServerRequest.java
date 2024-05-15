package com.inet.framework.servers;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.google.gson.Gson;
import com.inet.framework.servers.contracts.ServerHeadersInterface;
import com.inet.framework.servers.contracts.ServerRequestInterface;
import com.inet.settings.Env;
import com.sun.net.httpserver.HttpExchange;

public class ServerRequest implements ServerRequestInterface {
    private final HttpExchange httpExchange;
    private final ServerHeaders headers;
    private final URI uri;
    private final ServerRequestParams params = new ServerRequestParams();

    public ServerRequest(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
        uri = httpExchange.getRequestURI();
        headers = new ServerHeaders(httpExchange.getRequestHeaders());
    }

    private Boolean isParam(String part) {
        return Env.PATTERN_ROUTE_PARAM.matcher(part).matches();
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

    public ServerRequestParams getParams() {
        return params;
    }

    public String getUri() {
        return uri.getPath();
    }

    public Map<String, String> getQuerys() {
        Map<String, String> querys = new HashMap<>();

        for (String query : uri.getQuery().split("&")) {
            String[] keyAndValue = query.split("=");
            querys.put(keyAndValue[0], keyAndValue[1]);
        }

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
