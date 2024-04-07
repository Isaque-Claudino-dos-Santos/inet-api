package com.inet.views.scenes;

import com.inet.enums.UserTypeEnum;
import com.inet.views.components.ButtonComponent;
import com.inet.views.components.InputComponent;
import com.inet.views.components.SelectOptionsComponent;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class UserRegisterScene {
    Pane pane = new Pane();
    VBox formBox = new VBox();

    public UserRegisterScene() {
        Text result = new Text("Result");
        InputComponent inputName = new InputComponent().setLabelText("Name");
        InputComponent inputEmail = new InputComponent().setLabelText("Email");
        InputComponent inputPassword = new InputComponent().setLabelText("Password");
        InputComponent inputConfirmPassword = new InputComponent().setLabelText("Confirm Password");
        SelectOptionsComponent selectType = new SelectOptionsComponent()
                .setLabel("User Type")
                .addItem(UserTypeEnum.CLIENT.getValue())
                .addItem(UserTypeEnum.CLIENT_MANAGER.getValue());
        ButtonComponent btnRegister = new ButtonComponent()
                .setText("Register \n");

        btnRegister.onClick((e) -> {
            String name = inputName.getValue();
            String email = inputEmail.getValue();
            String password = inputPassword.getValue();
            String confirmPassword = inputConfirmPassword.getValue();
            String type = selectType.getValue();
            result.setText(
                    "Name: " + name + "\n" +
                            "E-mail: " + email + "\n" +
                            "Password: " + password + "\n" +
                            "Confirm Password: " + confirmPassword + "\n" +
                            "Type : " + type + "\n");
        });

        formBox.getChildren().addAll(
                inputName.getNode(),
                inputEmail.getNode(),
                inputPassword.getNode(),
                inputConfirmPassword.getNode(),
                selectType.getNode(),
                btnRegister.getNode(),
                result);
        pane.getChildren().add(formBox);
    }

    public Scene getScene() {
        return new Scene(pane, 650, 500);
    }
}