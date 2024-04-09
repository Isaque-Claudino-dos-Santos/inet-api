package com.inet.views.scenes;

import com.inet.AppControllers;
import com.inet.dto.UserStoreDTO;
import com.inet.enums.UserTypeEnum;
import com.inet.views.components.ButtonComponent;
import com.inet.views.components.InputComponent;
import com.inet.views.components.SelectOptionsComponent;
import com.libs.validation.exceptions.ValidationException;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class UserRegisterScene {
        Pane pane = new Pane();
        VBox formBox = new VBox();

        InputComponent inputName = new InputComponent();
        InputComponent inputEmail = new InputComponent();
        InputComponent inputPassword = new InputComponent();
        InputComponent inputConfirmPassword = new InputComponent();
        SelectOptionsComponent selectType = new SelectOptionsComponent();
        ButtonComponent btnRegister = new ButtonComponent();

        private void settingComponents() {
                inputName.setLabelText("Name");
                inputEmail.setLabelText("Email");
                inputPassword.setLabelText("Password");
                inputConfirmPassword.setLabelText("Confirm Password");
                selectType
                                .setLabel("User Type")
                                .addItem(UserTypeEnum.CLIENT.getValue())
                                .addItem(UserTypeEnum.CLIENT_MANAGER.getValue());
                btnRegister.setText("Register \n");
        }

        private void applyNodes() {
                formBox.getChildren().addAll(
                                inputName.getNode(),
                                inputEmail.getNode(),
                                inputPassword.getNode(),
                                inputConfirmPassword.getNode(),
                                selectType.getNode(),
                                btnRegister.getNode());
                pane.getChildren().add(formBox);
        }

        public UserRegisterScene() {
                settingComponents();

                btnRegister.onClick((event) -> {
                        try {
                                UserStoreDTO data = new UserStoreDTO();

                                data.setName(inputName.getValue());
                                data.setEmail(inputEmail.getValue());
                                data.setPassword(inputPassword.getValue());
                                data.setConfirmPassword(inputConfirmPassword.getValue());
                                data.setType(selectType.getValue());

                                AppControllers.userController.store(data);
                        } catch (ValidationException e) {
                                System.out.println(e.getMessage());
                        } catch (Exception e) {
                                System.out.println(e.getMessage());
                        }
                });

                applyNodes();
        }

        public Scene getScene() {
                return new Scene(pane, 650, 500);
        }
}