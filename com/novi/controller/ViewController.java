package com.novi.controller;

import com.novi.model.Player;
import com.novi.model.PlayerObserver;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable {
    @FXML
    Text player1Name, player2Name, player1Score, player2Score, currentPlayer;

    @FXML
    AnchorPane checkersPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Player player1 = PlayerController.getPlayer1();
        Player player2 = PlayerController.getPlayer2();

        PlayerTextObserver observer = new PlayerTextObserver();
        player1.registerObserver(observer);
        player2.registerObserver(observer);

        player1Name.setText(player1.getName());
        player2Name.setText(player2.getName());

        player1Score.setText(player1.getScore() + "");
        player2Score.setText(player2.getScore() + "");

        new CheckersController(player1, player2).init(checkersPane, currentPlayer);
    }

    private class PlayerTextObserver implements PlayerObserver {
        @Override
        public void update(Player player) {
            if (player == PlayerController.getPlayer1()) {
                player1Score.setText(player.getScore() + "");
            } else {
                player2Score.setText(player.getScore() + "");
            }
        }
    }
}
