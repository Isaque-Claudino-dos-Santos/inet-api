package com.inet.repositories;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.inet.AppConfig;
import com.inet.database.Mysql;
import com.inet.models.ClientModel;
import com.inet.utils.FileUtils;

public class ClientRepository {
    static private final Mysql mysql = new Mysql();

    static public void save(ClientModel data) {
        Connection conn = null;

        try {
            conn = mysql.getConnection();

            String query = FileUtils.readAll(AppConfig.env.DIR_QUERY + "/client-query/client-insert.sql");
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, data.name);
            statement.setString(2, data.systemName);
            statement.setString(3, data.systemArch);
            statement.setString(4, data.systemVersion);

            statement.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
