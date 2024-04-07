package com.inet.models;

import com.inet.enums.UserTypeEnum;

public class UserModel {
    public String name;
    public String email;
    public String password;
    public UserTypeEnum type = UserTypeEnum.ADMIN;

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "E-mail: " + email + "\n" +
                "Password: " + password + "\n" +
                "Type: " + type.getValue() + "\n";
    }
}