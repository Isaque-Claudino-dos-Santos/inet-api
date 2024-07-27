package com.framework.databases.contracts;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBInterface {
    public Connection open() throws SQLException;

    public void close() throws SQLException;
}
