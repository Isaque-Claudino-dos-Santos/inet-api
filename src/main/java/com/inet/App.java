package com.inet;

import com.inet.database.Mysql;
import com.inet.servers.AppServerSocket;

public class App {
    static final Mysql mysql = new Mysql();
    static final AppServerSocket appServerSocket = new AppServerSocket();

    public static void main(String[] args) {
        mysql.runAllMigrations(AppConfig.migrations);
    }
}
