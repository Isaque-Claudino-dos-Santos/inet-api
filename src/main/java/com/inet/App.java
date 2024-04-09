package com.inet;

import com.inet.servers.AppServerSocket;
import com.inet.views.Scenes;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    static final AppServerSocket appServerSocket = new AppServerSocket();

    public static void main(String[] args) {
        AppConfig.mysql.runAllMigrations(AppConfig.migrations);
        
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setScene(Scenes.userRegisterScene);
        stage.show();
    }

}
