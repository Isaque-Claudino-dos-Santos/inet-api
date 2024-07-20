package com.framework.server;

import com.framework.server.contracts.MiddlewareInterface;
import com.framework.server.response.ServerResponse;
import com.framework.server.response.modules.Response;

public abstract class Middleware implements MiddlewareInterface {
    private boolean goNext = false;

    public abstract Response handle(ServerRequest request, ServerResponse response) throws Exception;

    public Response next() {
        goNext = true;
        return null;
    }

    public Boolean notShouldGoNext() {
        return !goNext;
    }

}
