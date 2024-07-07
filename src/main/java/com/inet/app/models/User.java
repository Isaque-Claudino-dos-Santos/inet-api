package com.inet.app.models;

import com.framework.databases.Model;
import com.inet.app.models.data.UserData;

public class User extends Model<UserData> {

    @Override
    public String table() {
        return "users";
    }

    @Override
    public Class<UserData> modelData() {
        return UserData.class;
    }

    @Override
    public String[] fields() {
        return new String[] { "name", "email" };
    }

}
