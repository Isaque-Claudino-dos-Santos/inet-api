package com.inet;

import com.inet.servers.AppServerSocket;
import com.inet.views.Scenes;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {
    static final AppServerSocket appServerSocket = new AppServerSocket();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setScene(Scenes.userRegisterScene);
        stage.show();
    }

}
