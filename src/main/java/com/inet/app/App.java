package com.inet.app;

import com.framework.databases.Persist.Persist;
import com.framework.server.Server;
import com.inet.settings.Env;
import com.inet.settings.Kernel;

public class App {
    public static final Server server = new Server(Env.API_HOST, Env.API_PORT);

    public static void main(String[] args) {
        Persist.getInstance().config.set(
                Env.DB_USER,
                Env.DB_PASSWORD,
                Env.DB_HOST,
                Env.DB_PORT,
                Env.DB_DATABASE,
                Env.DB_DRIVES
        );

        Kernel kernel = new Kernel(server);

        kernel.__boot__();
    }
}
