package com.inet.enums;

import com.inet.exceptions.EnumNotFouldException;

public enum UserTypeEnum {
    ADMIN("admin"), CLIENT("client"), CLIENT_MANAGER("client-manager");

    private final String value;

    UserTypeEnum(String value) {
        this.value = value;
    }

    static public UserTypeEnum toEnum(String value) throws EnumNotFouldException {
        for (UserTypeEnum type : values()) {
            if (value == type.getValue()) {
                return type;
            }
        }

        throw new EnumNotFouldException(value);
    }

    public String getValue() {
        return value;
    }
}