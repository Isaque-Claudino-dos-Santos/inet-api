package com.framework.databases.contracts;

import com.framework.databases.Model;

import java.util.List;

public interface PersistStatementInterface {

    PersistQueryStatementInterface select(String... columns);

    PersistQueryStatementInterface select();

    PersistQueryStatementInterface update(List<String> columns, Object data);

    PersistQueryStatementInterface delete();

    PersistQueryStatementInterface create(Object data);
}
