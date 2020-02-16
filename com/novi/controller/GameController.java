package com.novi.controller;

import com.novi.model.Player;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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
}
