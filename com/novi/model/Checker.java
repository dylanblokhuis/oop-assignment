package com.novi.model;

import com.novi.controller.CheckersController;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author Dylan Blokhuis
 * @date 30-1-2020
 * Leerlijn: Object Oriented Programmeren
 */
public class Checker extends StackPane {
    private int columnIndex, rowIndex;
    private boolean isBlack;

    public Checker(int columnIndex, int rowIndex, boolean isBlack) {
        this.isBlack = isBlack;
        Circle stone = new Circle(15, isBlack ? Color.BLACK : Color.WHITE);

        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
        getChildren().add(stone);
    }

    public boolean isBlack() {
        return isBlack;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }
}
