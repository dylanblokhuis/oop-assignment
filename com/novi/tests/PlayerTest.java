package com.novi.tests;

import com.novi.models.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void getName() {
        System.out.println("getName");
        Player player = new Player("Dylan");
        String name = "Dylan";
        String result = player.getName();
        assertEquals(name, result);
    }

    @Test
    public void score() {
        System.out.println("getScore");
        Player player = new Player("Dylan");
        int startValue = 0;
        int result = player.getScore();
        assertEquals(startValue, result);

        System.out.println("addScore");
        player.addScore();
        result = player.getScore();
        int newScore = 1;
        assertEquals(newScore, result);
    }
}