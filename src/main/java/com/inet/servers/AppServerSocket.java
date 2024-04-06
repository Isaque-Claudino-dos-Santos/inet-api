package com.inet.servers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.inet.AppConfig;

public class AppServerSocket {
    private ServerSocket serverSocket;
    private Integer port;

    public AppServerSocket() {
        port = AppConfig.env.SOCKET_PORT;
    }

    public void connect() {
        try {
            serverSocket = new ServerSocket(port);
            handleSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleSocket() throws IOException {
        Socket socket = serverSocket.accept();

        while (true) {
            System.out.println(socket.getInetAddress());
        }
    }
}