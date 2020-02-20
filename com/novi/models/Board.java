package com.novi.models;

import com.novi.controllers.CheckersController;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 * @author Dylan Blokhuis
 * @date 6-2-2020
 * Leerlijn: Object Oriented Programmeren
 */
public class Board extends GridPane {
    public Board(int size) {
        setPrefWidth(size * Tile.SIZE);
        setPrefHeight(size * Tile.SIZE);

        for (int i = 0; i < size; i++) {
            addRow(i);
            addColumn(i);
        }
    }

    public int getCheckerTypeAmount(CheckerType checkerType) {
        int count = 0;

        for (int rowIndex = 0; rowIndex < CheckersController.SIZE; rowIndex++) {
            for (int columnIndex = 0; columnIndex < CheckersController.SIZE; columnIndex++) {
                Tile tile = getTile(columnIndex, rowIndex);
                if (tile.hasChecker() && tile.getChecker().getCheckerType() == checkerType) {
                    count++;
                }
            }
        }

        return count;
    }

    public Tile getTile(int column, int row) {
        Tile result = null;
        ObservableList<Node> children = getChildren();

        for (Node node : children) {
            if (getColumnIndex(node) == column && getRowIndex(node) == row && node instanceof Tile) {
                result = (Tile) node;
                break;
            }
        }

        return result;
    }
}
