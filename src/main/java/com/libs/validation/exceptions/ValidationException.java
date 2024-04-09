package com.libs.validation.exceptions;

public class ValidationException extends Exception {
    private String type;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, String type) {
        super("Validation Type: " + type + " " + message);
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
