package com.novi.controllers;

import com.novi.models.Player;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.Arrays;

/**
 * @author Dylan Blokhuis
 * @date 30-1-2020
 * Leerlijn: Object Oriented Programmeren
 */
public abstract class GameController {
    protected Player player1;
    protected Player player2;
    protected Text currentPlayerText;
    protected Player currentPlayer;

    public GameController(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void switchPlayers() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    public abstract void init(AnchorPane pane, Text currentPlayerText);

    protected abstract void startTurn(Player player);

    protected abstract void endTurn();

    protected abstract void restart();

    protected void setWinner(Player player) {
        String modalTitle = player.getName() + " won!";
        String modalMessage = "Congratulations, " + player.getName() + " you won!";
        ModalController modal = new ModalController(modalTitle, modalMessage);

        Button closeButton = new Button("Close");
        Button restartButton = new Button("Restart");

        closeButton.setOnMousePressed(event -> modal.close());
        restartButton.setOnMousePressed(event -> {
            modal.close();
            restart();
        });

        modal.addNodes(Arrays.asList(closeButton, restartButton));
        modal.showAndWait();
    }
}
