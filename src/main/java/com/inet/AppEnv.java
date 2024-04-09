package com.inet;

import java.util.regex.Pattern;

import io.github.cdimascio.dotenv.Dotenv;

public class AppEnv {
    private final Dotenv env = Dotenv.load();

    // general
    public final String JAVA_ENV = env.get("JAVA_ENV", "development");

    // database
    public final String DB_USER = env.get("DB_USER");
    public final String DB_PASSWORD = env.get("DB_PASSWORD");
    public final String DB_DATABASE = env.get("DB_DATABASE");
    public final String DB_HOST = env.get("DB_HOST", "127.0.0.1");
    public final String DB_PORT = env.get("DB_PORT", "3306");
    public final String DB_DRIVE = env.get("DB_DRIVE", "jdbc:mysql");

    // socket
    public final Integer SOCKET_PORT = Integer.parseInt(env.get("SOCKET_PORT", "3000"));

    // directory
    public final String DIR_ROOT = "src/main/java/com/inet";
    public final String DIR_DATABASE = DIR_ROOT + "/database";
    public final String DIR_QUERY = DIR_DATABASE + "/query";
    public final String DIR_MIGRATIONS = DIR_DATABASE + "/migrations";

    // regex pattern
    public final Pattern REGEX_EMAIL = Pattern.compile("^(\\w|\\.|-)*(@\\w*\\.\\w*)*$");
}
