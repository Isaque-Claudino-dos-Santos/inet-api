package com.framework.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RouteTest {

    @Test
    void it_should_create_route_correctly() {
        Route route = new Route("GET", "/", (req, res) -> true);

        assertEquals("/-get", route.getId());
        assertEquals("GET", route.getMethod());
        assertEquals("/", route.getUri());
        assertTrue(route.getResponseAction().execute(null, null));
    }
}
