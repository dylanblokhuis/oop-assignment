package com.novi.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Dylan Blokhuis
 * @date 02/02/2020
 * Leerlijn: Object Oriented Programmeren
 */
public class Tile extends Rectangle {
    public static final int SIZE = 45;
    private Checker checker;

    public Tile(int x, int y, boolean odd) {
        setWidth(SIZE);
        setHeight(SIZE);
        setFill(odd ? Color.MOCCASIN : Color.SANDYBROWN);
        relocate(x * SIZE, y * SIZE);
    }

    public void setChecker(Checker checker) {
        this.checker = checker;
    }

    public Checker getChecker() {
        return checker;
    }
}
