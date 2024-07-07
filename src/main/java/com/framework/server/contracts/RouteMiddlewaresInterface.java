package com.framework.server.contracts;

import java.util.Iterator;

public interface RouteMiddlewaresInterface extends Iterator<MiddlewareInterface> {
    public void reset();
}
