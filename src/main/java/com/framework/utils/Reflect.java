package com.framework.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reflect {
    public static <T> T newInstance(Class<T> objectClass, Class<?>[] parameterTypes, Object... args) {
        T objectInstance = null;

        try {
            objectInstance = objectClass.getDeclaredConstructor(parameterTypes).newInstance(args);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return objectInstance;
    }

    public static <T> void fieldSetValue(T obj, String fieldName, Object value) {
        Field field = null;

        try {
            field = obj.getClass().getDeclaredField(fieldName);
            field.set(obj, value);
        } catch (NoSuchFieldException exception) {
            System.err.println("\nException: " + exception.getClass().getName());
            System.err.println("Error: " + " field " + fieldName + " not found ");
            System.err.println("class: " + obj.getClass().getName());
            System.err.println("field: " + fieldName);
            System.err.println("value: " + value);
        } catch (IllegalAccessException exception) {
            System.err.println("\nException: " + exception.getClass().getName());
            System.err.println("Error: " + " field " + fieldName + " not accessible ");
            System.err.println("class: " + obj.getClass().getName());
            System.err.println("field: " + fieldName);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    ;

    public static List<Field> getFields(Object obj) {
        List<Field> fields = new ArrayList<>();

        try {
            fields = Arrays.asList(obj.getClass().getDeclaredFields());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return fields;
    }

    ;

    public static Field getField(Object obj, String fieldName) {
        try {
            return obj.getClass().getDeclaredField(fieldName);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public static Object getFieldValue(Object obj, String fieldName, Object data) {
        try {
            return getField(obj, fieldName).get(data);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public static void setReturnValue(Object obj, String methodName, Object returnValue) {
        try {
            obj.getClass().getMethod("data").invoke(methodName, returnValue);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public static Object invoke(Method method, Object obj, Object... args) {
        try {
            return method.invoke(obj, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
