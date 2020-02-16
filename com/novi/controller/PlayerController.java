package com.novi.controller;

import com.novi.model.Player;

/**
 * @author Dylan Blokhuis
 * @date 12-1-2020
 * Leerlijn: Object Oriented Programmeren
 */
public class PlayerController {
    private static Player player1;
    private static Player player2;

    public static Player getPlayer1() {
        return player1;
    }

    public static Player getPlayer2() {
        return player2;
    }

    public void setPlayer1(Player player) {
        player1 = player;
    }

    public void setPlayer2(Player player) {
        player2 = player;
    }
}
