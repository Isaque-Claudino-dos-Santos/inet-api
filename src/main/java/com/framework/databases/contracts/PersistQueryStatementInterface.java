package com.framework.databases.contracts;

import java.util.List;

public interface PersistQueryStatementInterface {
    PersistQueryStatementInterface where(String column, String operator, Object value);

    PersistQueryStatementInterface whereOr(String column, String operator, Object value);

    PersistQueryStatementInterface limit(Integer value);


    PersistQueryStatementInterface orderByAsc(String column);

    PersistQueryStatementInterface orderByDesc(String column);

    <T> T first(Class<T> dataType);

    <T> T getById(Object value, Class<T> dataType);

    <T> T last(Class<T> dataType);

    <T> List<T> all(Class<T> dataType);

    void exec();
}
