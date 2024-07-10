package com.framework.facades;

import com.framework.server.HttpClientRequest;

public class Http {
    public static HttpClientRequest get(String url) {
        return new HttpClientRequest("GET", url);
    }

    public static HttpClientRequest head(String url) {
        return new HttpClientRequest("HEAD", url);
    }

    public static HttpClientRequest post(String url) {
        return new HttpClientRequest("POST", url);
    }

    public static HttpClientRequest put(String url) {
        return new HttpClientRequest("PUT", url);
    }

    public static HttpClientRequest delete(String url) {
        return new HttpClientRequest("DELETE", url);
    }

    public static HttpClientRequest connect(String url) {
        return new HttpClientRequest("CONNECT", url);
    }

    public static HttpClientRequest options(String url) {
        return new HttpClientRequest("OPTIONS", url);
    }

    public static HttpClientRequest trace(String url) {
        return new HttpClientRequest("TRACE", url);
    }

    public static HttpClientRequest patch(String url) {
        return new HttpClientRequest("PATCH", url);
    }
}
