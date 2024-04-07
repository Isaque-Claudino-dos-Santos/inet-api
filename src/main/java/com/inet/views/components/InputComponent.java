package com.inet.views.components;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class InputComponent {
    // ELEMENTS
    private final HBox $box = new HBox();
    private final Text $label = new Text();
    private final TextField $field = new TextField();

    // STYLES
    private final Insets boxPadding = new Insets(5);

    public InputComponent() {
        $label.setOnMouseClicked((e) -> $field.requestFocus());
        $box.setPadding(boxPadding);
    }

    public InputComponent setLabelText(String text) {
        $label.setText(text);
        return this;
    }

    public String getValue() {
        return $field.getText();
    }

    public Node getNode() {
        $box.getChildren().setAll($label, $field);
        return $box;
    }
}