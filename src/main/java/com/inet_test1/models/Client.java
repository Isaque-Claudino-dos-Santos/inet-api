package com.inet_test1.models;

import com.inet_test1.data.ClientData;

public class Client {
    final ClientData data;

    public Client(ClientData data) {
        this.data = data;
    }

    public Client create(ClientData data) {
        Client client = new Client(data);
        System.out.println("Client Criado");
        System.out.println(client);
        return client;
    }

    @Override
    public String toString() {
        return "name: " + data.name + "\n" +
                "systemName: " + data.systemName + "\n" +
                "systemArch: " + data.systemArch + "\n" +
                "systemVersion: " + data.systemVersion + "\n";
    }
}