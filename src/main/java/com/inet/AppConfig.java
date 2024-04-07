package com.inet;

import com.inet.database.Mysql;

public class AppConfig {
    public static final AppEnv env = new AppEnv();
    public static final Mysql mysql = new Mysql();

    public static final String[] migrations = {
            "create-table-clients.sql",
            "create-table-users.sql"
    };
}
