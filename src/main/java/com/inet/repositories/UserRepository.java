package com.inet.repositories;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.inet.AppConfig;
import com.inet.database.Mysql;
import com.inet.models.UserModel;
import com.inet.utils.FileUtils;

public class UserRepository {
    public static final Mysql mysql = AppConfig.mysql;
    public static final String DIR_QUERY = AppConfig.env.DIR_QUERY + "/user-query";

    public static ArrayList<UserModel> findAll() {
        ArrayList<UserModel> users = new ArrayList<>();

        try {
            Connection conn = mysql.getConnection();
            String query = FileUtils.readAll(DIR_QUERY + "/user-select-all.sql");
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                UserModel user = new UserModel();
                user.setName(result.getString("name"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setType(result.getString("type"));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static void save(UserModel data) {
        try {
            Connection conn = mysql.getConnection();
            String query = FileUtils.readAll(DIR_QUERY + "/user-insert.sql");

            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, data.getName());
            statement.setString(2, data.getEmail());
            statement.setString(3, data.getPassword());
            statement.setString(4, data.getType());
            
            statement.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }
}