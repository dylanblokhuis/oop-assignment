package com.novi.tests;

import com.novi.models.CheckerType;
import com.novi.models.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void name() {
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

    @Test
    public void checkerType() {
        System.out.println("getCheckerType");
        Player player = new Player("Dylan");
        assertNull(player.getCheckerType());

        System.out.println("setCheckerType");
        player.setCheckerType(CheckerType.CLEAR);
        CheckerType testCheckerType = CheckerType.CLEAR;
        assertEquals(player.getCheckerType(), testCheckerType);
    }
}