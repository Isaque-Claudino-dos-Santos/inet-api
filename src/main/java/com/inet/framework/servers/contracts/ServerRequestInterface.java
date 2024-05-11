package com.inet.framework.servers.contracts;

import java.util.Map;

public interface ServerRequestInterface {
    public String getUri();

    public Map<String, String> getQuerys();

    public ServerHeadersInterface getHeaders();

    public String getMethod();
}
