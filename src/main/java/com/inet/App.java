package com.inet;

import com.inet.database.Mysql;
import com.inet.models.ClientModel;
import com.inet.repositories.ClientRepository;

public class App {
    static final Mysql mysql = new Mysql();

    public static void main(String[] args) {
        mysql.runAllMigrations(AppConfig.migrations);

        ClientModel client = new ClientModel();
        client.name = "Test";
        client.systemArch = "32 bits";
        client.systemName = "Windowns";
        client.systemVersion = "16.2";

        ClientRepository.save(client);
    }
}
