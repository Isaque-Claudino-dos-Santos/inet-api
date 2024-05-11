package com.inet;

import com.inet.framework.servers.Server;
import com.inet.settings.Kernel;

public class App {
    public static void main(String[] args) {
        Server server = new Server();

        Kernel kernel = new Kernel(server);

        kernel.__boot__();
    }
}
