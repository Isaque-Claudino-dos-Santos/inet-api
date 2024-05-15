package com.inet.framework.servers.enums;

public enum MethodEnum {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    OPTION("OPTION"),
    PATCH("PATCH");

    public String value;

    MethodEnum(String value) {
        this.value = value;
    }
}
