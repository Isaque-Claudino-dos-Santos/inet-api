package com.inet.views.components;

import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class SelectOptionsComponent {
    private final HBox $box = new HBox();
    private final Text $label = new Text();
    private final ChoiceBox<String> $select = new ChoiceBox<>();

    public SelectOptionsComponent addItem(String text) {
        $select.getItems().add(text);
        return this;
    }

    public SelectOptionsComponent setLabel(String text) {
        $label.setText(text);
        $box.getChildren().add($label);
        return this;
    }

    public String getValue() {
        return $select.valueProperty().get();
    }

    public Node getNode() {
        $box.getChildren().add($select);
        return $box;
    }
}