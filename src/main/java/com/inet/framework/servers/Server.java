package com.inet.framework.servers;

import com.inet.framework.servers.contracts.ServerConnectInterface;
import com.inet.framework.servers.contracts.ServerInterface;
import com.inet.framework.servers.contracts.ServerRoutesInterface;
import com.inet.framework.utils.ConsoleColors;
import com.inet.settings.Env;
import com.sun.net.httpserver.HttpServer;

public class Server implements ServerInterface {
    private final ServerConnect connect = new ServerConnect(Env.API_HOST, Env.API_PORT);
    private final ServerRoutes routes = new ServerRoutes();

    public Server() {
        HttpServer server = connect.getServer();

        server.createContext("/", routes);
    }

    public ServerConnectInterface getConnect() {
        return connect;
    }

    public void start() {
        connect.start();

        if (Env.JAVA_ENV.equals("enviroment")) {
            System.out.println("\nServer started");
            System.out
                    .println(ConsoleColors.GREEN + "http://" + Env.API_HOST + ":" + Env.API_PORT + ConsoleColors.RESET);
        }
    }

    public ServerRoutesInterface getServerRoutes() {
        return routes;
    }
}
