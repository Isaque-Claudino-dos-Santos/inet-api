package com.framework.server.enums;

public enum StatusEnum {
    SUCCESS(200),
    NOT_FOUND(404),
    NOT_CONTENT(204);

    public Integer value;

    StatusEnum(Integer status) {
        this.value = status;
    }

}
