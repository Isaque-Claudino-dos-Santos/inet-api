package com.framework.databases.contracts.lambdas;

import java.sql.Connection;

public interface PersistBuildLambda {
    public <T> T execute(Connection connection);
}
