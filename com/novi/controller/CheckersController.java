package com.novi.controller;

import com.novi.model.Board;
import com.novi.model.Checker;
import com.novi.model.Player;
import com.novi.model.Tile;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

/**
 * @author Dylan Blokhuis
 * @date 30-1-2020
 * Leerlijn: Object Oriented Programmeren
 */
public class CheckersController extends GameController {
    private static final int SIZE = 10;

    private Board board = new Board(SIZE);
    private Checker selectedChecker;
    private Tile targetTile;
    private ArrayList<Tile> availableTiles = new ArrayList<Tile>();

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
                addTileListeners(tile);
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

    private void addTileListeners(Tile tile) {
        tile.setOnMousePressed(event -> {
            System.out.println("Tile location column: " + tile.getColumnIndex());
            System.out.println("Tile location row: " + tile.getRowIndex());
            if (availableTiles.contains(tile)) {
                tile.setChecker(selectedChecker);
            }
        });
    }

    private void addCheckerListeners(Checker checker) {
        checker.setOnMousePressed(event -> {
            System.out.println("Checker location column: " + checker.getColumnIndex());
            System.out.println("Checker location row: " + checker.getRowIndex());

            /*
                check if a checker is already selected
                if that's the case remove the availability circles
             */
            if (selectedChecker != null) {
                availableTiles.forEach(Tile::removeAvailability);
                availableTiles.clear();
            }

            selectedChecker = checker;
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
            // check if index is out of the board's bounds
            if (columnIndex < 0 || columnIndex > (SIZE - 1)) {
                continue;
            }

            Tile adjacentTile = board.getTile(nextRow, columnIndex);
            boolean isTileOccupied = adjacentTile.hasChecker();

            if (!isTileOccupied) {
                adjacentTile.showAvailability();
                availableTiles.add(adjacentTile);
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
