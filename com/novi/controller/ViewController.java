package com.novi.controller;

import com.novi.model.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable {
    @FXML
    Text player1Text, player2Text, player1Score, player2Score, currentPlayer;

    @FXML
    TabPane tabs;

    @FXML
    Tab checkersTab;

    @FXML
    AnchorPane checkersPane;

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
                if (newValue == checkersTab) {
                    new CheckersController(player1, player2).init(checkersPane, currentPlayer);
                }
            }
        );
    }
}
