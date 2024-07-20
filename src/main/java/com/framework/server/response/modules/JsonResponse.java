package com.framework.server.response.modules;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JsonResponse extends Response {
    private final Gson gson = new Gson();

    private String formatValue() {
        Object data = getData();

        if (data == null) return "";

        return data instanceof String ? (String) data : gson.toJson(data);
    }

    @Override
    public void send() throws IOException {
        String value = formatValue();
        byte[] valueBytes = value.getBytes(StandardCharsets.UTF_8);

        headers.add("Content-Type", "application/json; charset=" + StandardCharsets.UTF_8);
        httpExchange.sendResponseHeaders(getStatus(), valueBytes.length);

        body.write(valueBytes);
        body.close();
    }
}
