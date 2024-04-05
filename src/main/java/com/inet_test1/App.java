package com.inet_test1;

import com.inet_test1.database.Mysql;

public class App {
    static final Mysql mysql = new Mysql();

    public static void main(String[] args) {
        mysql.runAllMigrations(AppConfig.migrations);
    }
}
