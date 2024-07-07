package com.inet.framework.databases.Persist;

import com.inet.framework.databases.contracts.DBInterface;
import com.inet.framework.databases.contracts.DBQueryInterface;

public class Persist implements DBInterface {
    private final PersistConnection connection = new PersistConnection();
    private final PersistQuery query = new PersistQuery();

    @Override
    public DBQueryInterface query() {
        return query;
    }

    @Override
    public PersistConnection connection() {
        return connection;
    }
}
