package com.inet.framework.servers.contracts;

import com.inet.framework.servers.ServerRequest;
import com.inet.framework.servers.ServerResponse;

public interface MiddlewareInterface {
    public Boolean handle(ServerRequest request, ServerResponse response);

    public Boolean next();
}
