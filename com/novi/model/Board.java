package com.novi.model;

import com.novi.controller.CheckersController;
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
                Tile tile = getTile(rowIndex, columnIndex);
                if (tile.hasChecker() && tile.getChecker().getCheckerType() == checkerType) {
                    count++;
                }
            }
        }

        return count;
    }

    public Tile getTile(int row, int column) {
        Tile result = null;
        ObservableList<Node> children = getChildren();

        for (Node node : children) {
            if (getRowIndex(node) == row && getColumnIndex(node) == column && node instanceof Tile) {
                result = (Tile) node;
                break;
            }
        }

        return result;
    }
}
