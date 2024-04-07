package com.inet.repositories;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.inet.AppConfig;
import com.inet.database.Mysql;
import com.inet.models.ClientModel;
import com.inet.utils.FileUtils;

public class ClientRepository {
    static private final Mysql mysql = AppConfig.mysql;
    static private final String DIR_QUERY = AppConfig.env.DIR_QUERY + "/client-query";

    static public ArrayList<ClientModel> index() {
        ArrayList<ClientModel> clients = new ArrayList<>();

        try {
            Connection conn = mysql.getConnection();
            String query = FileUtils.readAll(DIR_QUERY + "/client-select-all.sql");

            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                ClientModel client = new ClientModel();
                client.name = result.getString("name");
                client.systemArch = result.getString("systemArch");
                client.systemName = result.getString("systemName");
                client.systemVersion = result.getString("systemVersion");
                clients.add(client);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return clients;
    }

    static public void save(ClientModel data) {
        try {
            Connection conn = mysql.getConnection();

            String query = FileUtils.readAll(DIR_QUERY + "/client-insert.sql");
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
