package com.framework.servers.contracts;

import com.framework.servers.ServerRequest;
import com.framework.servers.ServerResponse;

public interface MiddlewareInterface {
    public Boolean handle(ServerRequest request, ServerResponse response);

    public Boolean next();
}
