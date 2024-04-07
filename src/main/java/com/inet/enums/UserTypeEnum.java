package com.inet.enums;

import com.inet.exceptions.EnumNotFouldException;

public enum UserTypeEnum {
    ADMIN("admin"), CLIENT("client"), CLIENT_MANAGER("client-manager");

    private final String value;

    UserTypeEnum(String value) {
        this.value = value;
    }

    static public UserTypeEnum toEnum(String e) throws EnumNotFouldException {
        for (UserTypeEnum value : values()) {
            if (e == value.getValue()) {
                return value;
            }
        }

        throw new EnumNotFouldException(e);
    }

    public String getValue() {
        return value;
    }
}