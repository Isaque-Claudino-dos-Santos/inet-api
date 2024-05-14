package com.inet.framework.utils;

public class Reflect {
    public static <T extends Object> T newInstance(Class<T> objectClass, Class<?>[] parameterTypes, Object... args) {
        T objectInstance = null;

        try {
            objectInstance = objectClass.getDeclaredConstructor(parameterTypes).newInstance(args);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return objectInstance;
    }
}
