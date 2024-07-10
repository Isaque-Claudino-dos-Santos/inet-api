package com.framework.constants.server.contracts;

import java.util.Map;

import com.framework.constants.server.ServerRequestParams;
import com.sun.net.httpserver.Headers;

public interface ServerRequestInterface {
    public String getUri();

    public Map<String, String> getQueries();

    public ServerRequestParams getParams();

    public String getMethod();

    public Headers getHeaders();
}
