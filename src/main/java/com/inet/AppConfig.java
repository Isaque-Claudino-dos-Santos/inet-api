package com.inet;

public class AppConfig {
    public static final AppEnv env = new AppEnv();

    public static final String[] migrations = {
            "create-table-clients.sql",
            "create-table-users.sql"
    };
}
