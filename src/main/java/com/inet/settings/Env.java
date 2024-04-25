package com.inet.settings;

import io.github.cdimascio.dotenv.Dotenv;

public class Env {
    private final static Dotenv env = Dotenv.load();

    // general
    public final static String JAVA_ENV = env.get("JAVA_ENV", "development");

    // database
    public final static String DB_USER = env.get("DB_USER");
    public final static String DB_PASSWORD = env.get("DB_PASSWORD");
    public final static String DB_DATABASE = env.get("DB_DATABASE");
    public final static String DB_HOST = env.get("DB_HOST", "127.0.0.1");
    public final static String DB_PORT = env.get("DB_PORT", "3306");
    public final static String DB_DRIVE = env.get("DB_DRIVE", "jdbc:mysql");

    // directory
    public final static String DIR_ROOT = "src/main/java/com/inet";
    public final static String DIR_DATABASE = DIR_ROOT + "/database";
    public final static String DIR_QUERY = DIR_DATABASE + "/query";
    public final static String DIR_MIGRATIONS = DIR_DATABASE + "/migrations";
}
