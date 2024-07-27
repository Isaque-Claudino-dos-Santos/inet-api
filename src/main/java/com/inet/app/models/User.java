package com.inet.app.models;

import com.framework.databases.Model;
import com.inet.app.models.data.UserData;

import java.util.List;

public class User extends Model {

    @Override
    public String table() {
        return "users";
    }

    @Override
    public List<String> columns() {
        return List.of("name", "email");
    }

}
