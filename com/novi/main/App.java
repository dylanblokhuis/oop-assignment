package com.novi.main;

import java.net.URL;

import com.novi.controller.SceneController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private static SceneController sceneController;

    @Override
    public void start(Stage stage) throws Exception {
        URL loginScenePath = getClass().getResource("/resources/fxml/Login.fxml");

        sceneController = new SceneController();
        sceneController.setStage(stage);
        sceneController.startScene(loginScenePath);
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static SceneController getSceneController()
    {
        return sceneController;
    }
}
