package com.framework.databases.Persist;

import com.framework.databases.contracts.DBInterface;
import com.framework.utils.ExceptionHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Consumer;

public class Persist implements DBInterface {
    public final PersistConfig config = new PersistConfig();
    private Connection connection = null;
    private static Persist instance = null;


    protected Persist() {
    }

    public static Persist getInstance() {
        if (instance == null) {
            instance = new Persist();
        }

        return instance;
    }

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
    public void open(Consumer<Connection> handler) {
        try {
            connection = DriverManager.getConnection(config.getUrl(), config.getUserCredentials());

            if (connection != null) {
                handler.accept(connection);
            }
        } catch (SQLException exception) {
            ExceptionHandler.print(exception);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException exception) {
                ExceptionHandler.print(exception);
            }
        }
    }

    @Override
    public void close() {
        try {
            if (connection == null || connection.isClosed()) return;

            connection.close();
        } catch (SQLException | NullPointerException exception) {
            ExceptionHandler.print(exception);
        }
    }
}
