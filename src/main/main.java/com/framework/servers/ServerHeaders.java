package com.framework.servers;

import com.framework.servers.contracts.ServerHeadersInterface;
import com.sun.net.httpserver.Headers;

public class ServerHeaders implements ServerHeadersInterface {
    private final Headers headers;

    public ServerHeaders(Headers headers) {
        this.headers = headers;
    }

    public Headers getHeaders() {
        return headers;
    }

    public Boolean has(String key) {
        return headers.containsKey(key);
    }

    public Boolean hasWith(String key, String value) {
        return headers.containsKey(key) && headers.get(key).contains(value);
    }
}
