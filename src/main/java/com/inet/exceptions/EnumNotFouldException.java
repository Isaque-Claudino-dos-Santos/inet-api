package com.inet.exceptions;

public class EnumNotFouldException extends Exception {
    public EnumNotFouldException() {
    }

    public EnumNotFouldException(String enumName) {
        super("Not Fould Enum " + enumName);
    }
}
