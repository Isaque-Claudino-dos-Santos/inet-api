package com.inet.framework.servers.contracts;

import java.util.Map;
import com.inet.framework.servers.Route;
import com.sun.net.httpserver.HttpHandler;

public interface ServerRoutesInterface extends HttpHandler, Map<String, Route> {
    public void add(Route route);

    public Boolean hasRoute(String key);

    public Boolean notHasRoute(String key);

}
