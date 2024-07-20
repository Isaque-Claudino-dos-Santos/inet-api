package com.framework.server;

import com.framework.server.response.modules.JsonResponse;
import com.framework.server.router.Route;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class RouteTest {

    @Test
    void it_should_create_route_correctly() throws Exception {
        Route route = new Route("GET", "/", (req, res) -> new JsonResponse());

        assertEquals("/-get", route.getId());
        assertEquals("GET", route.getMethod());
        assertEquals("/", route.getUri());
        assertInstanceOf(JsonResponse.class, route.getResponseAction().execute(null, null));
    }
}
