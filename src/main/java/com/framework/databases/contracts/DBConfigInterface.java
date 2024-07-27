package com.framework.databases.contracts;

import java.util.Properties;

public interface DBConfigInterface {
    String getUrl();

    Properties getUserCredentials();

    /**
     * Set configurations
     */
    void set(String user, String password, String host, Integer port, String databaseName, String driver);
}
