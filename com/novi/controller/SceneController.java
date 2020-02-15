package com.novi.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SceneController {
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

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    public Stage getStage()
    {
        return this.stage;
    }
}
