package com.novi.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Dylan Blokhuis
 * @date 11-1-2020
 * Leerlijn: Object Oriented Programmeren
 */
public class StageController {
    private Stage stage;

    public void startScene(String path, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/resources/styles/Styles.css").toExternalForm());

        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return this.stage;
    }
}
