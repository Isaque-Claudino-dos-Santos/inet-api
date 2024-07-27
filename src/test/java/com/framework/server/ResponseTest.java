package com.framework.server;

import com.framework.server.router.Route;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Optional;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResponseTest {
    static Server server;

    @BeforeAll
    public static void before_all_start_server() {
        server = new Server();
        server.configure("localhost", 3001);
        server.start();
    }

    @AfterEach
    public void after_each_clean_routes() {
        server.getServerRoutes().clear();
    }

    private static Stream<Arguments> responsesDataProvider() {
        Object[] json = new String[]{"json", "{\"name\": \"Hello World\"}", "application/json; charset=UTF-8"};
        Object[] csv = new String[]{"csv", "message\n 1 hello, , wold", "text/csv"};

        return Stream.of(Arguments.of(json), Arguments.of(csv));
    }

    @ParameterizedTest
    @MethodSource("responsesDataProvider")
    public void it_should_return_response_successfully(String type, String data, String expectedHeader) {
        Route route = new Route("GET", "/", (req, res) -> {
            return switch (type) {
                case "json" -> res.json(data, 200);
                case "csv" -> res.csv(data, 200);
                default -> null;
            };
        });

        server.getServerRoutes().add(route);

        HttpClientRequest http = new HttpClientRequest("GET", "http://localhost:3001").send();
        Optional<String> contentTypeHeader = http.getResponse().headers().firstValue("content-type");

        assertEquals(data, http.body(String.class));
        assertEquals(200, http.getResponse().statusCode());
        assertTrue(contentTypeHeader.isPresent());
        assertEquals(expectedHeader, contentTypeHeader.get());
    }
}
