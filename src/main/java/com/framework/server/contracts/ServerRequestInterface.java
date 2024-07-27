package com.framework.server.contracts;

import java.util.Map;

import com.framework.server.ServerRequestParams;
import com.sun.net.httpserver.Headers;

public interface ServerRequestInterface {
    public String getUri();

    public Map<String, String> getQueries();
    
    public String getMethod();

    public Headers getHeaders();
}
