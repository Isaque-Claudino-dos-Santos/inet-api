package com.inet.framework.servers;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import com.sun.net.httpserver.*;
import com.inet.framework.servers.contracts.ServerConnectInterface;

public class ServerConnect implements ServerConnectInterface {
    private String host = "localhost";
    private Integer port = 3000;
    private InetSocketAddress address;
    private HttpServer server = null;

    public ServerConnect() {
        super();
    }

    public ServerConnect(String host, Integer port) {
        try {
            this.host = InetAddress.getByName(host).getHostAddress();
            this.port = port;
            this.address = new InetSocketAddress(host, port);
            makeServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void makeServer() {
        try {
            server = HttpServer.create(address, 1);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
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

    public void start() {
        server.start();
    }

    public HttpServer getServer() {
        return server;
    }
}
