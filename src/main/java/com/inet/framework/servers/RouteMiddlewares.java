package com.inet.framework.servers;

import java.util.ArrayList;

import com.inet.framework.servers.contracts.RouteMiddlewaresInterface;

public class RouteMiddlewares extends ArrayList<Middleware> implements RouteMiddlewaresInterface {
    private Integer index = -1;

    public boolean hasNext() {
        return index < size() - 1;
    }

    public Middleware next() {
        Middleware middleware = null;
        
        try {
            middleware = get(++index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return middleware;
    }

}
