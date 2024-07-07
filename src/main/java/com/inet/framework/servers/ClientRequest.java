package com.inet.framework.servers;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ClientRequest {
    private final Gson gson = new Gson();
    protected HttpRequest request = null;
    protected HttpRequest.Builder requestBuilder = null;
    protected HttpResponse<String> response = null;
    protected HttpClient client = null;
    protected URI uri = null;
    protected HttpRequest.BodyPublisher data = HttpRequest.BodyPublishers.ofString("");

    public ClientRequest(String method, String url) {
        uri = URI.create(url);
        requestBuilder = HttpRequest.newBuilder(uri).method(method, data);
        client = HttpClient.newHttpClient();
    }

    /**
     * Add header
     */
    public ClientRequest header(String key, String value) {
        requestBuilder.header(key, value);
        return this;
    }

    /**
     * Set timeout
     */
    public ClientRequest timeout(Duration duration) {
        requestBuilder.timeout(duration);
        return this;
    }

    /**
     * Set data to request
     */
    public <D> ClientRequest data(D content) {
        data = HttpRequest.BodyPublishers.ofString(gson.toJson(content));
        return this;
    }

    /**
     * Send request
     */
    public ClientRequest send() {
        try {
            request = requestBuilder.build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
        return this;
    }

    /**
     * Get body response
     */
    public <T> T body(Class<T> modelClass) {
        if (response == null) return null;

        return modelClass.equals(String.class) || modelClass.equals(Integer.class) || modelClass.equals(Boolean.class) ? (T) response.body() : gson.fromJson(response.body(), modelClass);
    }

    /**
     * Get HttpRequest instance
     */
    public HttpRequest getRequest() {
        return request;
    }

    /**
     * Get HttpResponse instance
     */
    public HttpResponse<String> getResponse() {
        return response;
    }
}
