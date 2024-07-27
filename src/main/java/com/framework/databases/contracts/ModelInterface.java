package com.framework.databases.contracts;

import com.framework.databases.Persist.Persist;

import java.util.List;

public interface ModelInterface {
    abstract String table();

    abstract List<String> columns();

    String primaryKey();


    PersistStatementInterface statement();
}
