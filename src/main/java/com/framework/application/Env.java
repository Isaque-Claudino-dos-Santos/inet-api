package com.framework.application;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.regex.Pattern;

public class Env {
    private final Dotenv env = Dotenv.load();

    public final String JAVA_ENV = env.get("JAVA_ENV", "development");

    // database
    public final String DB_USER = env.get("DB_USER");
    public final String DB_PASSWORD = env.get("DB_PASSWORD");
    public final String DB_DATABASE = env.get("DB_DATABASE");
    public final String DB_HOST = env.get("DB_HOST", "127.0.0.1");
    public final Integer DB_PORT = Integer.parseInt(env.get("DB_PORT", "3306"));
    public final String DB_DRIVES = env.get("DB_DRIVES", "jdbc:mysql");

    // directory
    public final String DIR_BASE = "/com/inet";

    // Http API
    public final Integer API_PORT = Integer.valueOf(env.get("API_PORT", "3000"));
    public final String API_HOST = env.get("API_HOST");
    public final String API_LANG = env.get("API_LANG", "en-us");

    // Patterns
    public final Pattern PATTERN_ROUTE_PARAM = Pattern.compile("\\{\\w*}", Pattern.MULTILINE);
}
