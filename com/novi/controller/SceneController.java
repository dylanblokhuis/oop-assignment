package com.novi.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SceneController {
    private Stage stage;

    public void startScene(URL path) throws IOException {
        Parent root = FXMLLoader.load(path);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("resources/styles/Styles.css");

        stage.setResizable(false);
        stage.setTitle("Minigames");
        stage.setScene(scene);
        stage.show();
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    public Stage getStage()
    {
        return this.stage;
    }
}
