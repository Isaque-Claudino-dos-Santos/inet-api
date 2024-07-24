package com.framework.databases.contracts;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;

public interface DBInterface {
    public Connection open() throws SQLException;

    public void open(Consumer<Connection> handler);

    public void close() throws SQLException;
}
