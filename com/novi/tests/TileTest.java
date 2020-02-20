package com.novi.tests;

import com.novi.models.Checker;
import com.novi.models.CheckerType;
import com.novi.models.Tile;
import org.junit.Test;

import static org.junit.Assert.*;

public class TileTest {
    @Test
    public void isDark() {
        Tile tile = new Tile(1, 1, true);
        assertTrue(tile.isDark());
    }

    @Test
    public void getColumnIndex() {
        Tile tile = new Tile(1, 1, true);

        assertEquals(tile.getColumnIndex(), 1);
    }

    @Test
    public void getRowIndex() {
        Tile tile = new Tile(1, 1, true);

        assertEquals(tile.getRowIndex(), 1);
    }

    @Test
    public void getChecker() {
        Tile tile = new Tile(1, 1, true);
        Checker checker = new Checker(CheckerType.CLEAR);
        tile.setChecker(checker);

        assertEquals(checker, tile.getChecker());
    }

    @Test
    public void hasChecker() {
        Tile tile = new Tile(1, 1, true);
        Checker checker = new Checker(CheckerType.CLEAR);
        tile.setChecker(checker);

        assertTrue(tile.hasChecker());
    }

    @Test
    public void availability() {
        Tile tile = new Tile(1, 1, true);
        tile.showAvailability();
        assertTrue(tile.isAvailable());
        tile.removeAvailability();
        assertFalse(tile.isAvailable());
    }
}