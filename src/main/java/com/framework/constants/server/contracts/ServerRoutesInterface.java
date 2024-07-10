package com.framework.constants.server.contracts;

import java.util.Map;
import com.framework.constants.server.router.Route;
import com.sun.net.httpserver.HttpHandler;

public interface ServerRoutesInterface extends HttpHandler, Map<String, Route> {
    public ServerRoutesInterface add(Route route);

    public Boolean hasRoute(String key);

    public Boolean notHasRoute(String key);

}
