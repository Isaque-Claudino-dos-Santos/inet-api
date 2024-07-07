package com.framework.server.contracts;

import com.framework.server.ServerRequest;
import com.framework.server.ServerResponse;

public interface MiddlewareInterface {
    public Boolean handle(ServerRequest request, ServerResponse response);

    public Boolean next();
}
