package com.inet_test1.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;

import com.google.protobuf.Option;
import com.inet_test1.AppConfig;

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
        return DriverManager.getConnection(drive + "://" + host + "/" + database, user, password);
    }

    public void runMigration(String file) {
        Connection conn = null;
        File queryFile = null;
        Scanner queryRead = null;
        String query = "";        

        try {
            conn = getConnection();
            queryFile = new File("src/main/java/com/inet_test1/database/migrations/" + file);
            queryRead = new Scanner(queryFile);

            while (queryRead.hasNextLine()) {
                query += queryRead.nextLine();
            }

            PreparedStatement statement = conn.prepareStatement(query);

            statement.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        queryRead.close();
    }

    public void runAllMigrations(String[] migrations) {
        for (String migration : migrations) {
            runMigration(migration);
        }
    }
}