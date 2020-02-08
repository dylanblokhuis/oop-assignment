package com.novi.model;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author Dylan Blokhuis
 * @date 30-1-2020
 * Leerlijn: Object Oriented Programmeren
 */
public class Checker extends StackPane {
    private boolean isBlack;

    public Checker(boolean isBlack) {
        this.isBlack = isBlack;
        Circle stone = new Circle(15, isBlack ? Color.BLACK : Color.WHITE);
        getChildren().add(stone);
    }

    public boolean isBlack() {
        return isBlack;
    }

    public int getColumnIndex() {
        Tile tile = (Tile) getParent();
        return tile.getColumnIndex();
    }

    public int getRowIndex() {
        Tile tile = (Tile) getParent();
        return tile.getRowIndex();
    }
}
