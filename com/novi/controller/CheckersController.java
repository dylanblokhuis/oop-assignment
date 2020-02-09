package com.novi.controller;

import com.novi.model.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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
    private ArrayList<Checker> captured = new ArrayList<Checker>();
    private Text currentPlayerText;

    public CheckersController(Player player1, Player player2) {
        super(player1, player2);
    }

    public void init(AnchorPane pane, Text currentPlayerText) {
        pane.setPrefSize(SIZE * Tile.SIZE, SIZE * Tile.SIZE);
        pane.getChildren().addAll(board);
        this.currentPlayerText = currentPlayerText;

        player1.setCheckerType(CheckerType.CLEAR);
        player2.setCheckerType(CheckerType.DARK);

        setBoard();
        startTurn(player1);
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
            checker = new Checker(CheckerType.DARK);
        } else if (rowIndex >= 6 && isEven) {
            checker = new Checker(CheckerType.CLEAR);
        }

        return checker;
    }

    private void addTileListeners(Tile tile) {
        tile.setOnMousePressed(event -> move(tile));
    }

    private void addCheckerListeners(Checker checker) {
        checker.setOnMousePressed(event -> {
            selectChecker(checker);
        });
    }

    private void setAvailableTiles(Checker checker, CheckerType checkerType) {
        int nextRow = checkerType == CheckerType.DARK ? checker.getRowIndex() + 1 : checker.getRowIndex() - 1;

        if (nextRow < 0 || nextRow > (SIZE - 1)) {
            return;
        }

        int left = checker.getColumnIndex() - 1;
        int right = checker.getColumnIndex() + 1;

        if (left < 0) {
            return;
        }

        if (right > 0) {
            return;
        }

        Tile adjacentTile = board.getTile(nextRow, left);
        if (!adjacentTile.hasChecker()) {
            availableTiles.add(adjacentTile);
        } else if (adjacentTile.hasChecker() && adjacentTile.getChecker().getCheckerType() != checkerType) {
            System.out.println("row: " + adjacentTile.getRowIndex());
            System.out.println("col: " + adjacentTile.getColumnIndex());
            captured.clear();
            captured.add(adjacentTile.getChecker());

            setAvailableTiles(adjacentTile.getChecker(), adjacentTile.getChecker().getCheckerType() == CheckerType.DARK ? CheckerType.CLEAR : CheckerType.DARK);
            System.out.println("captured:" + captured.toString());
        }
    }

    private void checkColumn(int rowIndex, int columnIndex) {

    }

//    private void captureChecker()

//    private void showChainedCaptures(Tile tile, CheckerType ch) {
//        System.out.println("chained");
//        int nextRow = isBlack ? tile.getRowIndex() + 1 : tile.getRowIndex() - 1;
//
//        int[] nextColumns = {
//            tile.getColumnIndex() - 1,
//            tile.getColumnIndex() + 1
//        };
//
//        for (int columnIndex : nextColumns) {
//            // check if index is out of the board's bounds
//            if (columnIndex < 0 || columnIndex > (SIZE - 1)) {
//                continue;
//            }
//
//            Tile adjacentTile = board.getTile(nextRow, columnIndex);
//
//            if (!adjacentTile.hasChecker()) {
//                adjacentTile.showAvailability();
//                availableTiles.add(adjacentTile);
//            } else if (adjacentTile.hasChecker() && adjacentTile.getChecker().isBlack() != isBlack) {
//                captured.clear();
//                captured.add(adjacentTile.getChecker());
//                setAvailableTiles(adjacentTile.getChecker(), !adjacentTile.getChecker().isBlack());
//                System.out.println("captured:" + captured.toString());
//            } else if (!captured.isEmpty()) {
//                showChainedCaptures(adjacentTile, isBlack);
//            }
//        }
//    }

    private void clearMoveOptions() {
        availableTiles.forEach(Tile::removeAvailability);
        availableTiles.clear();
    }

    private void startTurn(Player player) {
        currentPlayer = player;
        currentPlayerText.setText(currentPlayer.getName());
        highlightMovableCheckers(player.getCheckerType());
    }

    private void highlightMovableCheckers(CheckerType checkerType) {
        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
            for (int columnIndex = 0; columnIndex < SIZE; columnIndex++) {
                Tile tile = board.getTile(rowIndex, columnIndex);
                if (tile.hasChecker() && tile.getChecker().getCheckerType() == checkerType) {
                    Checker checker = tile.getChecker();
                    availableTiles.clear();
                    setAvailableTiles(checker, checker.getCheckerType());

                    if (availableTiles.size() > 0) {
                        checker.highlight();
                    }
                }
            }
        }
    }

    private void removeHighlights(CheckerType checkerType) {
        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
            for (int columnIndex = 0; columnIndex < SIZE; columnIndex++) {
                Tile tile = board.getTile(rowIndex, columnIndex);
                if (tile.hasChecker() && tile.getChecker().getCheckerType() == checkerType) {
                    tile.getChecker().removeHighlight();
                }
            }
        }
    }

    private void selectChecker(Checker checker) {
        captured.clear();

        if (checker.getCheckerType() != currentPlayer.getCheckerType() || !checker.isHighlighted()) {
            return;
        }

        if (selectedChecker != null) {
            selectedChecker.deselect();
            clearMoveOptions();
        } else {
            availableTiles.clear();
        }

        selectedChecker = checker;
        selectedChecker.selected();
        setAvailableTiles(selectedChecker, selectedChecker.getCheckerType());
        availableTiles.forEach(Tile::showAvailability);
    }

    private void move(Tile tile) {
        if (selectedChecker == null) {
            return;
        }

        if (availableTiles.contains(tile)) {
            removeHighlights(selectedChecker.getCheckerType());

            tile.setChecker(selectedChecker);
            clearMoveOptions();
            selectedChecker.deselect();
            selectedChecker = null;

            if (captured.size() > 0) {
                captured.forEach(checker -> {
                    Tile tileOfCaptured = (Tile) checker.getParent();
                    tileOfCaptured.getChildren().remove(checker);
                    currentPlayer.addToCaptured(checker);
                });
                captured.clear();
            }

            endTurn();
        }
    }

    private void endTurn() {
        switchPlayers();
        startTurn(currentPlayer);
    }
}
