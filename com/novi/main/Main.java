package com.novi.main;

import com.novi.controller.SceneController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Dylan Blokhuis
 * @date 10-1-2020
 * Leerlijn: Object Oriented Programmeren
 */
public class Main extends Application {
    private static SceneController sceneController;

    @Override
    public void start(Stage stage) throws Exception {
        sceneController = new SceneController();
        sceneController.setStage(stage);
        sceneController.startScene("/resources/fxml/Login.fxml", "Welcome to checkers!");
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
