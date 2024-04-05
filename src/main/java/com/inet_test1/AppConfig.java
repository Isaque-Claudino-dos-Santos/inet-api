package com.inet_test1;

import io.github.cdimascio.dotenv.Dotenv;

public class AppConfig {
    public static final Dotenv env = Dotenv.load();
    static final String[] migrations = {
            "create-table-clients.sql"
    };
}
