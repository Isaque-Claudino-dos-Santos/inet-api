package com.inet.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.inet.AppConfig;
import com.inet.utils.FileUtils;

import io.github.cdimascio.dotenv.Dotenv;

public class Mysql {
    private final String drive;
    private final String host;
    private final String port;
    private final String database;
    private final String user;
    private final String password;

    public Mysql() {
        Dotenv env = AppConfig.env;
        drive = env.get("DB_DRIVE", "jdbc");
        host = env.get("DB_HOST", "127.0.0.1");
        port = env.get("DB_PORT", "3306");
        database = env.get("DB_DATABASE");
        user = env.get("DB_USER");
        password = env.get("DB_PASSWORD");
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(drive + "://" + host + ":" + port + "/" + database, user, password);
    }

    public void runMigration(String file) {
        try {
            Connection conn = getConnection();
            String query = FileUtils.readAll(AppConfig.DIR_MIGRATIONS + "/" +file);

            PreparedStatement statement = conn.prepareStatement(query);

            statement.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void runAllMigrations(String[] migrations) {
        for (String migration : migrations) {
            runMigration(migration);
        }
    }
}