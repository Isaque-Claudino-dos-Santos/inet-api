package com.framework.constants.server.response.modules;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CsvResponse extends Response {
    private String formatValue() {
        Object data = getData();
        String value = "";

        if (data instanceof String) {
            value = (String) data;
        }

        return value;
    }

    @Override
    public void send() throws IOException {
        String value = formatValue();
        byte[] valueBytes = value.getBytes(StandardCharsets.UTF_8);

        headers.add("content-type", "text/csv");
        httpExchange.sendResponseHeaders(getStatus(), valueBytes.length);

        body.write(valueBytes);
        body.close();
    }
}
