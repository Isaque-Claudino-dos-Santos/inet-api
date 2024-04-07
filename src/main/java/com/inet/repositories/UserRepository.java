package com.inet.repositories;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.inet.AppConfig;
import com.inet.database.Mysql;
import com.inet.enums.UserTypeEnum;
import com.inet.exceptions.EnumNotFouldException;
import com.inet.models.UserModel;
import com.inet.utils.FileUtils;

public class UserRepository {
    public static final Mysql mysql = AppConfig.mysql;
    public static final String DIR_QUERY = AppConfig.env.DIR_QUERY + "/user-query";

    public static ArrayList<UserModel> index() {
        ArrayList<UserModel> users = new ArrayList<>();

        try {
            Connection conn = mysql.getConnection();
            String query = FileUtils.readAll(DIR_QUERY + "/user-select-all.sql");
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                UserModel user = new UserModel();
                user.name = result.getString("name");
                user.email = result.getString("email");
                user.password = result.getString("password");
                user.type = UserTypeEnum.toEnum(result.getString("type"));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EnumNotFouldException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static void save(UserModel data) {
        try {
            Connection conn = mysql.getConnection();
            String query = FileUtils.readAll(DIR_QUERY + "/user-insert.sql");

            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(0, data.name);
            statement.setString(1, data.email);
            statement.setString(2, data.password);
            statement.setString(3, data.type.getValue());

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }
}