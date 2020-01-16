package com.novi.controller;

import com.novi.main.App;
import com.novi.model.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private Player player1;
    private Player player2;

    @FXML
    TextField textFieldPlayer1, textFieldPlayer2;

    @FXML
    Button startButton;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        textFieldPlayer1.textProperty().addListener((observable, oldValue, newValue) -> {
            player1 = new Player(newValue);

            startButton.setDisable(!isReady());
        });

        textFieldPlayer2.textProperty().addListener((observable, oldValue, newValue) -> {
            player2 = new Player(newValue);

            startButton.setDisable(!isReady());
        });

        textFieldPlayer1.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER && isReady()) {
                try {
                    transitionScene();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        textFieldPlayer2.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER && isReady()) {
                try {
                    transitionScene();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public boolean isReady()
    {
        if (player1 != null && !player1.getName().isEmpty() && player2 != null && !player2.getName().isEmpty()) {
            return true;
        } else if (!startButton.isDisabled()) {
            return false;
        } else {
            return false;
        }
    }

    public void handleStartButton() throws IOException {
        transitionScene();
    }

    private void transitionScene() throws IOException {
        PlayerController playerController = new PlayerController();
        playerController.setPlayer1(player1);
        playerController.setPlayer2(player2);

        URL mainScenePath = getClass().getResource("/resources/fxml/Main.fxml");
        SceneController sceneController = App.getSceneController();
        sceneController.startScene(mainScenePath);
    }
}
