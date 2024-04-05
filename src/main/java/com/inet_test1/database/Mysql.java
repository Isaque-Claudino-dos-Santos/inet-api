package com.inet_test1.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Mysql {
    Connection conn;

    public Mysql() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "test", "Test@123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void runMigration(String file) {
        File queryFile = null;
        Scanner queryRead = null;
        String query = "";

        try {
            queryFile = new File("src/main/java/com/inet_test1/database/migrations/" + file);
            queryRead = new Scanner(queryFile);

            while (queryRead.hasNextLine()) {
                query += queryRead.nextLine();
            }

            PreparedStatement statement = conn.prepareStatement(query);

            statement.execute();
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