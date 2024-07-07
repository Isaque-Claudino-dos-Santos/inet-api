package com.inet.framework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RunWith(FrameworkTestRunner.class)
public class ServerTest {
    @Test
    public void it_should_start_server() throws Exception {
        assertTrue(true);

        HttpRequest request = HttpRequest.newBuilder(URI.create("http://localhost:3001")).GET().build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(response.body(), "Hello World");

    }
}
