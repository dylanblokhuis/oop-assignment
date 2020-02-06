package com.novi.controller;

import com.novi.model.Board;
import com.novi.model.Checker;
import com.novi.model.Player;
import com.novi.model.Tile;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;

/**
 * @author Dylan Blokhuis
 * @date 30-1-2020
 * Leerlijn: Object Oriented Programmeren
 */
public class CheckersController extends GameController {
    private static final int SIZE = 10;

    private Board board = new Board(SIZE);
//    private Tile[][] board = new Tile[SIZE][SIZE];
//    private Group tiles = new Group();
//    private Group checkers = new Group();

    public CheckersController(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    public void init(AnchorPane pane) {
        pane.setPrefSize(SIZE * Tile.SIZE, SIZE * Tile.SIZE);
        pane.getChildren().addAll(board);

        setBoard();
    }

    private void setBoard() {
        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
            for (int columnIndex = 0; columnIndex < SIZE; columnIndex++) {
                Tile tile = createTile(columnIndex, rowIndex);
                board.add(tile, columnIndex, rowIndex);

                Checker checker = createChecker(columnIndex, rowIndex);

                if (checker != null) {
                    Tile boardTile = board.getTile(rowIndex, columnIndex);

                    boardTile.setChecker(checker);
                    addCheckerListeners(boardTile.getChecker());
                }
            }
        }
    }

    private Tile createTile(int x, int y) {
        boolean isTileOdd = (x + y) % 2 == 0;

        return new Tile(x, y, isTileOdd);
    }

    private Checker createChecker(int columnIndex, int rowIndex) {
        Checker checker = null;

        boolean isEven = (columnIndex + rowIndex) % 2 != 0;

        // should probably make this scale with size
        if (rowIndex < 4 && isEven) {
            checker = new Checker(columnIndex, rowIndex, true);
        } else if (rowIndex >= 6 && isEven) {
            checker = new Checker(columnIndex, rowIndex, false);
        }

        return checker;
    }

    private void addCheckerListeners(Checker checker) {
        checker.setOnMousePressed(event -> {
            System.out.println("Checker location column: " + checker.getColumnIndex());
            System.out.println("Checker location row: " + checker.getRowIndex());

            showMoveOptions(checker);
        });
    }

    private void showMoveOptions(Checker checker) {
        int nextRow = checker.isBlack() ? checker.getRowIndex() + 1 : checker.getRowIndex() - 1;

        int[] nextColumns = {
            checker.getColumnIndex() - 1,
            checker.getColumnIndex() + 1
        };

        for (int columnIndex: nextColumns) {
            Tile tempTile = board.getTile(nextRow, columnIndex);
            boolean isTileOccupied = tempTile.hasChecker();

            if (!isTileOccupied) {
                tempTile.showAvailability();
            }
        }
    }

    private boolean checkMove(double x, double y, Checker checker) {
        System.out.println(x);
        System.out.println(y);
        System.out.println(checker.getColumnIndex());
        System.out.println(checker.getRowIndex());

        return true;
    }
}
