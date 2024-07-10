package com.inet.app;

import com.inet.app.databases.Mysql;
import com.framework.server.Server;
import com.inet.settings.Env;
import com.inet.settings.Kernel;

public class App {
    public static final Mysql mysql = new Mysql();

    public static void main(String[] args) {
        Server server = new Server(Env.API_HOST, Env.API_PORT);

        Kernel kernel = new Kernel(server);


        kernel.__boot__();
    }
}
