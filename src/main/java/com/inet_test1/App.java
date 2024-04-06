package com.inet_test1;

import com.inet_test1.database.Mysql;
import com.inet_test1.models.ClientModel;
import com.inet_test1.repositories.ClientRepository;

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
