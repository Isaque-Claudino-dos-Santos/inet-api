package com.framework.databases.Persist;

import java.util.Properties;

import com.framework.databases.contracts.DBConfigInterface;

public class PersistConfig implements DBConfigInterface {
    private Integer port = null;
    private String host = null;
    private String user = null;
    private String driver = null;
    private String password = null;
    private String databaseName = null;

    @Override
    public void set(String user, String password, String host, Integer port, String databaseName, String driver) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
        this.driver = driver;
    }

    @Override
    public String getUrl() {
        return "jdbc:" + driver + "://" + host + ":" + port + "/" + databaseName;
    }

    @Override
    public Properties getUserCredentials() {
        Properties credentials = new Properties();

        credentials.put("user", user);
        credentials.put("password", password);

        return credentials;
    }
}
