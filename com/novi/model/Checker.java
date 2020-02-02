package com.novi.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author Dylan Blokhuis
 * @date 30-1-2020
 * Leerlijn: Object Oriented Programmeren
 */
public class Checker extends Circle {
    private int x = 0;
    private int y = 0;

    public Checker(int x, int y, Color color) {
        super(15, color);

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
