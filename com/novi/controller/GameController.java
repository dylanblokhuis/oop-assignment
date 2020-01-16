package com.novi.controller;

import com.novi.model.Game;
import com.novi.model.Player;

public class GameController {
    protected Player player1;
    protected Player player2;

    protected Player currentPlayer;

    public GameController(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void init() {
        System.out.println("Bulls & Cows");

        currentPlayer = player1;

        int difficulty = 1;
        Game game;

        while (true) {
            System.out.println("Score " + player1.getScore() + " | " + player1.getScore());

            if (currentPlayer.getScore() == 5) {
                System.out.println(currentPlayer.getName() + " has won!");
                break;
            }

            while (true) {
                game = new Game(difficulty);
                boolean isGameWon = game.play(currentPlayer);

                if (isGameWon) {
                    difficulty++;
                    break;
                } else {
                    switchPlayers();
                }
            }
        }
    }

    public void switchPlayers() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }
}
