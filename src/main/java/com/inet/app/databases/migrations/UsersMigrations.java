package com.inet.app.databases.migrations;

import com.inet.framework.databases.Persist.table.Migration;

public class UsersMigrations extends Migration {

    public void up() {
        scheme().create("users", (column) -> {
            column.id();
            column.string("name", 45).unique();
            column.string("email", 150).unique();
            column.timestamp();
        });
    }

    public void destroy() {
        scheme().delete("users");
    }

}
