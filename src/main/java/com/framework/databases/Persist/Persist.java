package com.framework.databases.Persist;

import com.framework.databases.contracts.DBInterface;
import com.framework.utils.ExceptionHandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class Persist implements DBInterface {
    public final PersistConfig config = PersistConfig.getInstance();
    private Connection connection = null;

    @Override
    public Connection open() {
        try {
            connection = DriverManager.getConnection(config.getUrl(), config.getUserCredentials());
        } catch (SQLException exception) {
            ExceptionHandler.print(exception);
        }

        return connection;
    }

    @Override
    public void close() {
        try {
            if (Objects.isNull(connection) || connection.isClosed()) return;

            connection.close();
        } catch (SQLException | NullPointerException exception) {
            ExceptionHandler.print(exception);
        }
    }
}
