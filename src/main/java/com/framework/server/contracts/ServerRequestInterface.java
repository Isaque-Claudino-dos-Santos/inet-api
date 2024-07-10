package com.framework.server.contracts;

import java.util.Map;

import com.framework.server.ServerRequestParams;

public interface ServerRequestInterface {
    public String getUri();

    public Map<String, String> getQuerys();

    public ServerHeadersInterface getHeaders();

    public ServerRequestParams getParams();

    public String getMethod();
}
