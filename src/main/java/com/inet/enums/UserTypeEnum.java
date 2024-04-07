package com.inet.enums;

public enum UserTypeEnum {
    ADMIN("admin"), CLIENT("client"), CLIENT_MANAGER("client-manager");

    private final String value;

    UserTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}