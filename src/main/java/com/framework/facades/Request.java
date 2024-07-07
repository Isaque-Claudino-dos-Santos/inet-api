package com.framework.facades;

import com.framework.servers.Http;

public class Request {
    public static Http get(String url) {
        return new Http("GET", url);
    }

    public static Http head(String url) {
        return new Http("HEAD", url);
    }

    public static Http post(String url) {
        return new Http("POST", url);
    }

    public static Http put(String url) {
        return new Http("PUT", url);
    }

    public static Http delete(String url) {
        return new Http("DELETE", url);
    }

    public static Http connect(String url) {
        return new Http("CONNECT", url);
    }

    public static Http options(String url) {
        return new Http("OPTIONS", url);
    }

    public static Http trace(String url) {
        return new Http("TRACE", url);
    }

    public static Http patch(String url) {
        return new Http("PATCH", url);
    }
}
