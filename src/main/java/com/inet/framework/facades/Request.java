package com.inet.framework.facades;

import com.inet.framework.servers.ClientRequest;

public class Request {
    public static ClientRequest get(String url) {
        return new ClientRequest("GET", url);
    }

    public static ClientRequest head(String url) {
        return new ClientRequest("HEAD", url);
    }

    public static ClientRequest post(String url) {
        return new ClientRequest("POST", url);
    }

    public static ClientRequest put(String url) {
        return new ClientRequest("PUT", url);
    }

    public static ClientRequest delete(String url) {
        return new ClientRequest("DELETE", url);
    }

    public static ClientRequest connect(String url) {
        return new ClientRequest("CONNECT", url);
    }

    public static ClientRequest options(String url) {
        return new ClientRequest("OPTIONS", url);
    }

    public static ClientRequest trace(String url) {
        return new ClientRequest("TRACE", url);
    }

    public static ClientRequest patch(String url) {
        return new ClientRequest("PATCH", url);
    }
}
