package com.framework.server.response;

import java.io.OutputStream;

import com.framework.server.contracts.ServerResponseInterface;
import com.framework.server.response.modules.JsonResponse;
import com.framework.server.response.modules.CsvResponse;
import com.framework.server.response.factories.ResponseFactory;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

public class ServerResponse implements ServerResponseInterface {
    private final ResponseFactory responseFactory;

    public ServerResponse(HttpExchange httpExchange) {
        OutputStream body = httpExchange.getResponseBody();
        Headers headers = httpExchange.getResponseHeaders();

        responseFactory = new ResponseFactory(httpExchange, body, headers);
    }


    @Override
    public CsvResponse csv(String data, Integer status) {
        return responseFactory.createCsvResponse(data, status);
    }

    @Override
    public JsonResponse json(Object data, Integer status) {
        return responseFactory.createJsonResponse(data, status);
    }
}
