package com.novi.controller;

import com.novi.main.App;
import com.novi.model.Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable {
    @FXML
    Text player1Text, player2Text, player1Score, player2Score;

    @FXML
    TabPane tabs;

    @FXML
    Tab snakeTab;

    @FXML
    AnchorPane snakePane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Player player1 = PlayerController.getPlayer1();
        Player player2 = PlayerController.getPlayer2();

        player1Text.setText(player1.getName());
        player2Text.setText(player2.getName());

        player1Score.setText(player1.getScore() + "");
        player2Score.setText(player2.getScore() + "");

        tabs.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue == snakeTab) {
                    GameController.boot(snakePane);
                }
            }
        );
    }
}
