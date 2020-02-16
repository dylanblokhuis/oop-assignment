package com.novi.controller;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

/**
 * @author Dylan Blokhuis
 * @date 15/02/2020
 * Leerlijn: Object Oriented Programmeren
 */
public class ModalController extends Stage {
    VBox box = new VBox(10);

    public ModalController(String title, String message) {
        setMinWidth(300);
        setTitle(title);
        initModality(Modality.APPLICATION_MODAL);

        box.prefHeightProperty().bind(heightProperty());
        box.prefWidthProperty().bind(widthProperty());
        box.setAlignment(Pos.CENTER);

        Scene scene = new Scene(box, 300, 200);
        Label modalMessage = new Label(message);

        box.getChildren().add(modalMessage);

        setScene(scene);
    }

    public void addNodes(List<Node> nodes) {
        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(nodes);
        hbox.setAlignment(Pos.CENTER);

        box.getChildren().add(hbox);
    }
}
