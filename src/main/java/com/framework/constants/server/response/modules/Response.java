package com.framework.constants.server.response.modules;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public abstract class Response {
    protected HttpExchange httpExchange = null;
    protected OutputStream body = null;
    protected Headers headers = null;
    protected Object data = null;
    protected Integer status = null;


    public void setData(Object data) {
        this.data = data;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setBody(OutputStream body) {
        this.body = body;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    public void setHttpExchange(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
    }

    public Headers getHeaders() {
        return headers;
    }

    public HttpExchange getHttpExchange() {
        return httpExchange;
    }

    public Integer getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

    public OutputStream getBody() {
        return body;
    }

    public abstract void send() throws IOException;
}
