package com.framework.constants.server;

import java.util.ArrayList;

import com.framework.constants.server.contracts.RouteMiddlewaresInterface;

public class RouteMiddlewares extends ArrayList<Middleware> implements RouteMiddlewaresInterface {
    private Integer index = -1;

    public void reset() {
        index = -1;
    }

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
