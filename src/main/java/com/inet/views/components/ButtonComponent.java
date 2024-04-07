package com.inet.views.components;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ButtonComponent {
    private final Button $button = new Button();

    public ButtonComponent setText(String text) {
        $button.setText(text);
        return this;
    }

    public void onClick(EventHandler<? super MouseEvent> callback) {
        $button.setOnMouseClicked(callback);
    }

    public Node getNode() {
        return $button;
    }
}
