package com.framework.server.response.factories;

import com.framework.server.response.modules.CsvResponse;
import com.framework.server.response.modules.JsonResponse;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;

public class ResponseFactory {
    private final OutputStream body;
    private final Headers headers;
    private final HttpExchange httpExchange;

    public ResponseFactory(HttpExchange httpExchange, OutputStream body, Headers headers) {
        this.httpExchange = httpExchange;
        this.body = body;
        this.headers = headers;
    }

    public CsvResponse createCsvResponse(String data, Integer status) {
        CsvResponse csvResponse = new CsvResponse();

        csvResponse.setData(data);
        csvResponse.setBody(body);
        csvResponse.setStatus(status);
        csvResponse.setHeaders(headers);
        csvResponse.setHttpExchange(httpExchange);

        return csvResponse;
    }

    public JsonResponse createJsonResponse(Object data, Integer status) {
        JsonResponse jsonResponse = new JsonResponse();

        jsonResponse.setData(data);
        jsonResponse.setBody(body);
        jsonResponse.setStatus(status);
        jsonResponse.setHeaders(headers);
        jsonResponse.setHttpExchange(httpExchange);

        return jsonResponse;
    }
}
