package com.framework.servers.contracts;

import java.util.Iterator;

public interface RouteMiddlewaresInterface extends Iterator<MiddlewareInterface> {
    public void reset();
}
