package com.framework.server.enums;

public enum StatusEnum {
    SUCCESS(200),
    NOT_FOUND(404),
    NOT_CONTENT(204),
    INTERNAL_ERROR(500);

    public final Integer value;

    StatusEnum(Integer status) {
        this.value = status;
    }

}
