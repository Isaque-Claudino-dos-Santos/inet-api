package com.framework.servers;

import com.framework.servers.contracts.MiddlewareInterface;

public abstract class Middleware implements MiddlewareInterface {
    private boolean goNext = false;

    public abstract Boolean handle(ServerRequest request, ServerResponse response);

    public Boolean next() {
        goNext = true;
        return true;
    }

    public Boolean notShouldGoNext() {
        return !goNext;
    }

}
