package com.framework.server;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientRequest {
    private final Gson gson = new Gson();
    protected HttpRequest request = null;
    protected HttpRequest.Builder requestBuilder = null;
    protected HttpResponse<String> response = null;
    protected HttpClient client = null;
    protected URI uri = null;
    protected HttpRequest.BodyPublisher data = HttpRequest.BodyPublishers.ofString("");

    public HttpClientRequest(String method, String url) {
        uri = URI.create(url);
        requestBuilder = HttpRequest.newBuilder(uri).method(method, data);
        client = HttpClient.newHttpClient();
    }

    /**
     * Add header
     */
    public HttpClientRequest header(String key, String value) {
        requestBuilder.header(key, value);
        return this;
    }

    /**
     * Set timeout
     */
    public HttpClientRequest timeout(Duration duration) {
        requestBuilder.timeout(duration);
        return this;
    }

    /**
     * Set data to request
     */
    public <D> HttpClientRequest data(D content) {
        data = HttpRequest.BodyPublishers.ofString(gson.toJson(content));
        return this;
    }

    /**
     * Send request
     */
    public HttpClientRequest send() {
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
    public <T> Object body(Class<T> modelClass) {
        if (response == null) return null;

        if (modelClass.equals(String.class)) {
            return String.valueOf(response.body());
        }

        if (modelClass.equals(Integer.class)) {
            return Integer.valueOf(response.body());
        }

        if (modelClass.equals(Boolean.class)) {
            return Boolean.valueOf(response.body());
        }

        return gson.fromJson(response.body(), modelClass);
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
