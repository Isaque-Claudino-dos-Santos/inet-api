package com.inet;

import com.inet.database.Mysql;
import com.inet.enums.UserTypeEnum;
import com.inet.models.ClientModel;
import com.inet.repositories.ClientRepository;
import com.inet.servers.AppServerSocket;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {
    static final AppServerSocket appServerSocket = new AppServerSocket();

    public static void main(String[] args) {
        launch();
        AppConfig.mysql.runAllMigrations(AppConfig.migrations);

        for (ClientModel client : ClientRepository.index()) {
            System.out.println(client);
            System.out.println("\n");
        }
    }

    private Parent createContent() {
        return new StackPane(new Text("Hello World"));
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent(), 300, 300));
        stage.show();
    }

}
