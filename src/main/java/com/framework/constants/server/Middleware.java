package com.framework.constants.server;

import com.framework.constants.server.contracts.MiddlewareInterface;
import com.framework.constants.server.response.ServerResponse;
import com.framework.constants.server.response.modules.Response;

public abstract class Middleware implements MiddlewareInterface {
    private boolean goNext = false;

    public abstract Response handle(ServerRequest request, ServerResponse response);

    public Response next() {
        goNext = true;
        return null;
    }

    public Boolean notShouldGoNext() {
        return !goNext;
    }

}
