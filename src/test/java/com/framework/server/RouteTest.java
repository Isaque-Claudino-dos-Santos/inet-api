package com.framework.server;

import com.framework.constants.server.response.modules.JsonResponse;
import com.framework.constants.server.router.Route;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class RouteTest {

    @Test
    void it_should_create_route_correctly() {
        Route route = new Route("GET", "/", (req, res) -> res.json(null, 200));

        assertEquals("/-get", route.getId());
        assertEquals("GET", route.getMethod());
        assertEquals("/", route.getUri());
        assertInstanceOf(JsonResponse.class, route.getResponseAction().execute(null, null));
    }
}
