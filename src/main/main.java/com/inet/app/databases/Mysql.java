package com.inet.app.databases;

import com.framework.databases.Persist.Persist;
import com.inet.settings.Env;

public class Mysql extends Persist {
    public Mysql() {
        connection().configure(
                Env.DB_DRIVES,
                Env.DB_HOST,
                Env.DB_PORT,
                Env.DB_DATABASE,
                Env.DB_USER,
                Env.DB_PASSWORD);
    }
}
