package com.inet;

import io.github.cdimascio.dotenv.Dotenv;

public class AppConfig {
    public static final Dotenv env = Dotenv.load();

    public static final String DIR_ROOT = "src/main/java/com/inet";
    public static final String DIR_DATABASE = DIR_ROOT + "/database";
    public static final String DIR_QUERY = DIR_DATABASE + "/query";
    public static final String DIR_MIGRATIONS = DIR_DATABASE + "/migrations";
    
    public static final String[] migrations = {
            "create-table-clients.sql"
    };
}
