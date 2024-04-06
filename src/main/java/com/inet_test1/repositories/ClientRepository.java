package com.inet_test1.repositories;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.inet_test1.database.Mysql;
import com.inet_test1.models.ClientModel;

public class ClientRepository {
    static private final Mysql mysql = new Mysql();

    static public void save(ClientModel data) {
        Connection conn = null;

        try {
            conn = mysql.getConnection();

            PreparedStatement statement = conn.prepareStatement("INSERT INTO clients (name, systemName, systemArch, systemVersion) VALUES (?, ?, ?, ?)");

            statement.setString(1, data.name);
            statement.setString(2, data.systemName);
            statement.setString(3, data.systemArch);
            statement.setString(4, data.systemVersion);

            statement.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
