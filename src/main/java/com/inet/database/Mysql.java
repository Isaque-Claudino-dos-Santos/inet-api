package com.inet.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.inet.AppConfig;
import com.inet.AppEnv;
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
        AppEnv env = AppConfig.env;
        drive = env.DB_DRIVE;
        host = env.DB_HOST;
        port = env.DB_PORT;
        database = env.DB_DATABASE;
        user = env.DB_USER;
        password = env.DB_PASSWORD;
    }

    public Connection getConnection() throws SQLException {

        return DriverManager.getConnection(drive + "://" + host + ":" + port + "/" + database, user, password);
    }

    public void runMigration(String file) {
        try {
            Connection conn = getConnection();
            String query = FileUtils.readAll(AppConfig.env.DIR_MIGRATIONS + "/" + file);

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