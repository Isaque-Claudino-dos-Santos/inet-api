package com.inet.models;

import com.inet.enums.UserTypeEnum;
import com.inet.exceptions.EnumNotFouldException;

public class UserModel {
    String name;
    String email;
    String password;
    UserTypeEnum type = UserTypeEnum.ADMIN;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return this.type.getValue();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        try {
            this.type = UserTypeEnum.toEnum(type);
        } catch (EnumNotFouldException e) {
            e.getStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "E-mail: " + email + "\n" +
                "Password: " + password + "\n" +
                "Type: " + type.getValue() + "\n";
    }
}