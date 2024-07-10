package com.framework.constants.server;

import com.framework.constants.server.contracts.ServerInterface;
import com.framework.constants.server.contracts.ServerRoutesInterface;
import com.framework.constants.server.router.ServerRoutes;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Server implements ServerInterface {
    private final ServerRoutes routes = new ServerRoutes();
    private String host = "localhost";
    private Integer port = 3000;
    private InetSocketAddress address;
    private HttpServer server = null;

    public Server(String host, Integer port) {
        try {
            this.host = InetAddress.getByName(host).getHostAddress();
            this.port = port;
            this.address = new InetSocketAddress(host, port);
            server = HttpServer.create(address, 1);
            server.createContext("/", routes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void start() {
        server.start();
    }

    public ServerRoutesInterface getServerRoutes() {
        return routes;
    }

    public Integer getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public HttpServer getServer() {
        return server;
    }
}
