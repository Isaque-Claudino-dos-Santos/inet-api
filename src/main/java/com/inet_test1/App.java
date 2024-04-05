package com.inet_test1;

import com.inet_test1.database.Mysql;

public class App {
    static final Mysql mysql = new Mysql();
    static final String[] migrations = {
            "create-table-clients.sql"
    };

    public static void main(String[] args) {
        mysql.runAllMigrations(migrations);
    }
}
