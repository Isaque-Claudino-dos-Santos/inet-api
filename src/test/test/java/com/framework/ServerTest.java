package java.com.framework;

import com.framework.facades.Request;
import com.framework.servers.ClientRequest;
import com.framework.servers.Route;
import com.framework.servers.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServerTest {
    static Server server;

    // TESTS FLOW

    @BeforeAll
    public static void before_all_start_server() {
        server = new Server("localhost", 3001);
        server.start();
    }

    @AfterEach
    public void after_each_clean_routes() {
        server.getServerRoutes().clear();
    }

    // TESTS

    @Test
    void must_create_route_successfully() {
        server.getServerRoutes().add(new Route("GET", "/", (req, res) -> res.setStatus(200).json("Hello World")));

        ClientRequest request = Request.get("http://localhost:3001" + "/").send();

        assertEquals("Hello World", request.body(String.class));
    }

    @Test
    void must_return_not_found_on_not_setting_route() {
        ClientRequest request = Request.get("http://localhost:3001" + "/").send();
        assertEquals("Not found 404", request.body(String.class));
    }
}
