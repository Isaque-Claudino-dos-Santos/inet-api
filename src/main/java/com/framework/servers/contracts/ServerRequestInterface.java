package com.framework.servers.contracts;

import java.util.Map;

import com.framework.servers.ServerRequestParams;

public interface ServerRequestInterface {
    public String getUri();

    public Map<String, String> getQuerys();

    public ServerHeadersInterface getHeaders();

    public ServerRequestParams getParams();

    public String getMethod();
}
