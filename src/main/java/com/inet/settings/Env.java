package com.inet.settings;

import io.github.cdimascio.dotenv.Dotenv;

public class Env {
    private final static Dotenv env = Dotenv.load();

    // database
    public final static String DB_USER = env.get("DB_USER");
    public final static String DB_PASSWORD = env.get("DB_PASSWORD");
    public final static String DB_DATABASE = env.get("DB_DATABASE");
    public final static String DB_HOST = env.get("DB_HOST", "127.0.0.1");
    public final static String DB_PORT = env.get("DB_PORT", "3306");
    public final static String DB_DRIVE = env.get("DB_DRIVE", "jdbc:mysql");

    // directory
    public final static String DIR_BASE = "src/main/java/com/inet";

    // Http API
    public final static Integer API_PORT = Integer.valueOf(env.get("API_PORT", "3000"));
    public final static String API_HOST = env.get("API_HOST");
}
