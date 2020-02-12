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
//    private ArrayList<Tile> availableTiles = new ArrayList<Tile>();
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

    private int getNextRow(CheckerType checkerType, int rowIndex) {
        int nextRow = checkerType == CheckerType.DARK ? rowIndex + 1 : rowIndex - 1;

        if (nextRow < 0 || nextRow > (SIZE - 1)) {
            return -1;
        }

        return nextRow;
    }

    private ArrayList<Tile> getAvailableTiles(Checker checker, Checker parent) {
        ArrayList<Tile> availableTiles = new ArrayList<>();
        CheckerType checkerType = checker.getCheckerType();

        if (parent != null) {
            checkerType = parent.getCheckerType();
        }

        int rowIndex = getNextRow(checkerType, checker.getRowIndex());
//        int columnIndex = getNextColumn(MoveDirection.LEFT, checker.getColumnIndex());
        int[] columnIndexes = {
                checker.getColumnIndex() - 1,
                checker.getColumnIndex() + 1
        };

        if (rowIndex == -1) {
            return availableTiles;
        }

        for (int columnIndex : columnIndexes) {
            if (columnIndex < 0 || columnIndex > (SIZE - 1)) {
                continue;
            }

            Tile adjacentTile = board.getTile(rowIndex, columnIndex);

            if (!adjacentTile.hasChecker()) {
                if (parent != null) {
                    if (isLeft(parent.getColumnIndex(), adjacentTile.getColumnIndex())) {
                        availableTiles.add(adjacentTile);
                    }
                } else {
                    availableTiles.add(adjacentTile);
                }

            } else if (adjacentTile.hasChecker() && adjacentTile.getChecker().getCheckerType() != checkerType) {
                System.out.println("YA");
                System.out.println("THE ONE ROW: " + adjacentTile.getRowIndex());
                System.out.println("THE ONE COL: " + adjacentTile.getColumnIndex());
                if (parent == null) {
                    availableTiles.addAll(getAvailableTiles(adjacentTile.getChecker(), checker));
                }
            }
        }

//        System.out.println("NEXT ROW: " + rowIndex);
//        System.out.println("NEXT COLUMN: " + columnIndex);


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
        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
            for (int columnIndex = 0; columnIndex < SIZE; columnIndex++) {
                Tile tile = board.getTile(rowIndex, columnIndex);
                if (tile.hasChecker() && tile.getChecker().getCheckerType() == checkerType) {
                    Checker checker = tile.getChecker();

                    if (getAvailableTiles(checker, null).size() > 0) {
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
