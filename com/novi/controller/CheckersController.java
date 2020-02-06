package com.novi.controller;

import com.novi.model.Checker;
import com.novi.model.Player;
import com.novi.model.Tile;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Dylan Blokhuis
 * @date 30-1-2020
 * Leerlijn: Object Oriented Programmeren
 */
public class CheckersController extends GameController {
    private static final int SIZE = 10;

    private Tile[][] board = new Tile[SIZE][SIZE];
    private Group tiles = new Group();
    private Group checkers = new Group();

    public CheckersController(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    public void init(AnchorPane pane) {
        pane.setPrefSize(SIZE * Tile.SIZE, SIZE * Tile.SIZE);
        pane.getChildren().addAll(tiles, checkers);

        setBoard();
    }

    private void setBoard() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                Tile tile = createTile(x, y);
                board[x][y] = tile;
                tiles.getChildren().add(tile);

                Checker checker = createChecker(x, y);

                if (checker != null) {
                    tile.setChecker(checker);
                    addMoveListeners(checker);
                    checkers.getChildren().add(checker);
                }
            }
        }
    }

    private Tile createTile(int x, int y) {
        boolean isTileOdd = (x + y) % 2 == 0;

        return new Tile(x, y, isTileOdd);
    }

    private Checker createChecker(int x, int y) {
        Checker checker = null;

        boolean isEven = (x + y) % 2 != 0;

        // should probably make this scale with size
        if (y < 4 && isEven) {
            checker = new Checker(x, y, Color.BLACK);
        } else if (y >= 6 && isEven) {
            checker = new Checker(x, y, Color.WHITE);
        }

        return checker;
    }

    private void addMoveListeners(Checker checker) {
        checker.setOnMouseReleased(event -> {
            // check move here
            if (true) {
//                checker.
            } else {
                checker.toOldPosition();
            }
        });
    }
}
