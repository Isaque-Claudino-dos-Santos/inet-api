package com.framework.servers;

import java.io.IOException;
import java.io.OutputStream;

import com.framework.servers.contracts.ServerResponseInterface;
import com.google.gson.Gson;
import com.framework.servers.enums.StatusEnum;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

public class ServerResponse implements ServerResponseInterface {
    private final Gson gson = new Gson();
    private final HttpExchange httpExchange;
    private final OutputStream body;
    private final Headers headers;
    private String charset = "UTF-8";
    private Integer status = 200;
    private Boolean wasAnswered = false;

    public ServerResponse(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
        body = httpExchange.getResponseBody();
        headers = httpExchange.getResponseHeaders();
    }

    public ServerResponse setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public ServerResponse setStatus(StatusEnum status) {
        this.status = status.value;
        return this;
    }

    public ServerResponse setCharSet(String charset) {
        this.charset = charset;
        return this;
    }

    public Boolean getWasAnswered() {
        return wasAnswered;
    }

    public void sendResponseHeaders(StatusEnum status, Integer length) {
        try {
            httpExchange.sendResponseHeaders(status.value, length);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void sendResponseHeaders(Integer status, Integer length) {
        try {
            httpExchange.sendResponseHeaders(status, length);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public Boolean noContent() {
        if (wasAnswered) {
            return true;
        }

        sendResponseHeaders(StatusEnum.NOT_CONTENT, -1);

        wasAnswered = true;
        return true;
    }

    public <T extends Object> Boolean json(T data) {
        if (wasAnswered) {
            return true;
        }

        try {
            String value = data instanceof String ? (String) data : gson.toJson(data);
            byte[] valueBytes = value.getBytes(charset);

            headers.add("Content-Type", "application/json; charset=" + charset);
            sendResponseHeaders(status, valueBytes.length);
            body.write(valueBytes);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                body.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        wasAnswered = true;
        return true;
    }

}
