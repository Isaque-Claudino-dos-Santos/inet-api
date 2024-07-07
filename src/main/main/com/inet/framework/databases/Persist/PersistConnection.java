package com.inet.framework.databases.Persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.inet.framework.databases.contracts.DBConnectionInterface;

public class PersistConnection implements DBConnectionInterface {
    private Connection connection = null;
    private String driver = null;
    private String host = null;
    private Integer port = null;
    private String databaseName = null;
    private String user = null;
    private String password = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public Connection open() {
        String url;
        try {
            url = "jdbc:" + driver + "://" + host + ":" + port + "/" + databaseName;
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return connection;
    }

    public Connection openReadonly() {
        try {
            open();
            connection.setReadOnly(true);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return connection;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }

            if (statement != null) {
                statement.close();
                statement = null;
            }

            if (resultSet != null) {
                resultSet.close();
                resultSet = null;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getUser() {
        return user;
    }

    public String getDriver() {
        return driver;
    }

    public Boolean isConnected() {
        Boolean connected = false;
        try {
            connected = !connection.isClosed() && connection.isValid(0);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return connected;
    }

    public Statement createStatement() {
        try {
            statement = connection.createStatement();
            return statement;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public ResultSet getResultSet() {
        try {
            resultSet = statement.getResultSet();
            return resultSet;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public void configure(String driver, String host, Integer port, String databaseName, String user, String password) {
        this.driver = driver;
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
        this.user = user;
        this.password = password;
    }

}
