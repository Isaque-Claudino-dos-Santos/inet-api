package com.framework.servers.contracts;

import com.sun.net.httpserver.Headers;

public interface ServerHeadersInterface {
    public Headers getHeaders();

    public Boolean has(String key);

    public Boolean hasWith(String key, String value);
}
