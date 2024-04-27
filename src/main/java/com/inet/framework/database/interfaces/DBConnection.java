package com.inet.framework.database.interfaces;

import java.sql.Connection;

public interface DBConnection {
    public Connection open();
    
    public Connection openReadyOnly();

    public void close();
}
