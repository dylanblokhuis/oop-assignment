package com.novi.controller;

import com.novi.model.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Dylan Blokhuis
 * @date 30-1-2020
 * Leerlijn: Object Oriented Programmeren
 */
public class CheckersController extends GameController {
    public static final int SIZE = 8;

    private Board board = new Board(SIZE);
    private Checker selectedChecker;
    private Checker captured;
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
        if (rowIndex < 3 && isEven) {
            checker = new Checker(CheckerType.DARK);
        } else if (rowIndex >= 5 && isEven) {
            checker = new Checker(CheckerType.CLEAR);
        }

        return checker;
    }

    private void addTileListeners(Tile tile) {
        tile.setOnMousePressed(event -> move(tile));
    }

    private void addCheckerListeners(Checker checker) {
        checker.setOnMousePressed(event -> selectChecker(checker));
    }

    private ArrayList<Integer> getNextRows(CheckerType checkerType, int rowIndex, boolean isKing) {
        ArrayList<Integer> nextRows = new ArrayList<>();

        if (isKing) {
            nextRows.add(checkerType == CheckerType.DARK ? rowIndex - 1 : rowIndex + 1);
        }

        nextRows.add(checkerType == CheckerType.DARK ? rowIndex + 1 : rowIndex - 1);

        return nextRows;
    }

    private ArrayList<Tile> getAvailableTiles(Checker checker, Checker parent) {
        ArrayList<Tile> availableTiles = new ArrayList<>();
        CheckerType checkerType = checker.getCheckerType();

        ArrayList<Integer> rowIndexes;

        if (parent != null) {
            rowIndexes = new ArrayList<>(
                    getNextRows(parent.getCheckerType(), checker.getRowIndex(), parent.isKing())
            );
        } else {
            rowIndexes = new ArrayList<>(
                    getNextRows(checkerType, checker.getRowIndex(), checker.isKing())
            );
        }

        int[] columnIndexes = {
                checker.getColumnIndex() - 1,
                checker.getColumnIndex() + 1
        };

        for (int rowIndex : rowIndexes) {
            if (rowIndex < 0 || rowIndex > (SIZE - 1)) {
                // skip loop if its out of the board's bounds
                continue;
            }

            for (int columnIndex : columnIndexes) {
                if (columnIndex < 0 || columnIndex > (SIZE - 1)) {
                    // skip loop if its out of the board's bounds
                    continue;
                }

                Tile adjacentTile = board.getTile(rowIndex, columnIndex);

                if (!adjacentTile.hasChecker()) {
                    if (parent != null) {
                        if (parent.getRowIndex() == adjacentTile.getRowIndex()) {
                            /*
                             * Skip this loop if parent shares the same row as the adjacent tile
                             * since this breaks the rules of checkers.
                             */
                            continue;
                        }

                        if (isLeft(parent.getColumnIndex(), adjacentTile.getColumnIndex())) {
                            captured = checker;
                            availableTiles.add(adjacentTile);
                        }

                        if (isRight(parent.getColumnIndex(), adjacentTile.getColumnIndex())) {
                            captured = checker;
                            availableTiles.add(adjacentTile);
                        }
                    } else {
                        if (captured == null) {
                            availableTiles.add(adjacentTile);
                        }
                    }
                } else if (adjacentTile.hasChecker() && adjacentTile.getChecker().getCheckerType() != checkerType) {
                    if (parent == null) {
                        availableTiles.addAll(getAvailableTiles(adjacentTile.getChecker(), checker));
                    }
                }
            }
        }

        return availableTiles;
    }

    private boolean isRight(int columnIndexOld, int columnIndexNew) {
        return (columnIndexOld + 2) == columnIndexNew;
    }

    private boolean isLeft(int columnIndexOld, int columnIndexNew) {
        return (columnIndexOld - 2) == columnIndexNew;
    }

    private void clearMoveOptions(Checker checker) {
        getAvailableTiles(checker, null).forEach(Tile::removeAvailability);
    }

    private void startTurn(Player player) {
        currentPlayer = player;
        currentPlayerText.setText(currentPlayer.getName());
        highlightMovableCheckers(player.getCheckerType());
    }

    private void highlightMovableCheckers(CheckerType checkerType) {
        ArrayList<Checker> highlightableCheckers = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
            for (int columnIndex = 0; columnIndex < SIZE; columnIndex++) {
                Tile tile = board.getTile(rowIndex, columnIndex);

                if (tile.hasChecker() && tile.getChecker().getCheckerType() == checkerType) {
                    Checker checker = tile.getChecker();

                    if (getAvailableTiles(checker, null).size() > 0) {
                        if (captured == null) {
                            highlightableCheckers.add(checker);
                        } else {
                            highlightableCheckers = new ArrayList<>(Arrays.asList(checker));
                        }
                    }
                }
            }
        }

        highlightableCheckers.forEach(Checker::highlight);
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
        if (checker.getCheckerType() != currentPlayer.getCheckerType() || !checker.isHighlighted()) {
            return;
        }

        if (selectedChecker != null) {
            selectedChecker.deselect();
            clearMoveOptions(selectedChecker);
        }

        selectedChecker = checker;
        selectedChecker.selected();
        getAvailableTiles(selectedChecker, null).forEach(Tile::showAvailability);
    }

    private void move(Tile tile) {
        if (selectedChecker == null) {
            return;
        }

        if (getAvailableTiles(selectedChecker, null).contains(tile)) {
            removeHighlights(selectedChecker.getCheckerType());
            clearMoveOptions(selectedChecker);
            tile.setChecker(selectedChecker);

            if (tile.getRowIndex() == 0 || tile.getRowIndex() == 7) {
                selectedChecker.makeKing();
            }

            selectedChecker.deselect();
            selectedChecker = null;

            if (captured != null) {
                Tile tileOfCaptured = (Tile) captured.getParent();
                if (!tileOfCaptured.getChildren().isEmpty()) {
                    tileOfCaptured.getChildren().remove(captured);
                    currentPlayer.addToCaptured(captured);
                }

                if (board.getCheckerTypeAmount(captured.getCheckerType()) == 0) {
                    currentPlayer.addScore();
                    setWinner(currentPlayer);
                }
            }

            captured = null;

            endTurn();
        }
    }

    private void endTurn() {
        switchPlayers();
        startTurn(currentPlayer);
    }

    private void setWinner(Player player) {
        ViewController.popupWindow(player.getName());
    }
}
