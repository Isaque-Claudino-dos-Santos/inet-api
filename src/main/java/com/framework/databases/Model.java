package com.framework.databases;

import com.framework.databases.Persist.Persist;
import com.framework.databases.Persist.PersistStatement;
import com.framework.databases.contracts.ModelInterface;
import com.framework.databases.contracts.PersistStatementInterface;

public abstract class Model implements ModelInterface {
    @Override
    public String primaryKey() {
        return "id";
    }

    @Override
    public PersistStatementInterface statement() {
        return new PersistStatement(this);
    }
}
