package com.framework.databases.contracts;

import java.sql.Connection;

public interface DBConnectionInterface {
    /**
     * Start connection
     * 
     * @return
     */
    public Connection open();

    /**
     * Start connection readonly
     * 
     * @return
     */
    public Connection openReadonly();

    /**
     * Stop connection
     */
    public void close();

    /**
     * Get connection host
     * 
     * @return
     */
    public String getHost();

    /**
     * Get connection port
     * 
     * @return
     */
    public Integer getPort();

    /**
     * Get connection database name
     * 
     * @return
     */
    public String getDatabaseName();

    /**
     * Get user connection
     * 
     * @return
     */
    public String getUser();

    /**
     * Get connection driver
     * 
     * @return
     */
    public String getDriver();

    /**
     * Return already connected
     * 
     * @return
     */
    public Boolean isConnected();

    /**
     * Configure credentials to connection
     * 
     * @param driver
     * @param host
     * @param port
     * @param databaseName
     * @param user
     * @param password
     */
    public void configure(String driver, String host, Integer port, String databaseName, String user, String password);
}
