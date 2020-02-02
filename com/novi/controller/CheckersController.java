package com.novi.controller;

import com.novi.model.Checker;
import com.novi.model.Player;
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


    public CheckersController(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    public void init(AnchorPane pane) {
        GridPane gp = new GridPane();
        gp.setGridLinesVisible(true);

        // create 8x8 gridpane
        for (int i = 0; i < SIZE; i++) {
            gp.addRow(i);
            gp.addColumn(i);
        }

        setGridPaneTiles(gp);
        setBoard(gp);

        pane.getChildren().add(gp);
    }

    private void setGridPaneTiles(GridPane gp) {
        // row loop
        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
            // column loop
            for (int columnIndex = 0; columnIndex < SIZE; columnIndex++) {
                Rectangle rect = new Rectangle(45, 45);

                if (rowIndex % 2 == 0) {
                    if (columnIndex % 2 == 0) {
                        rect.setFill(Color.SANDYBROWN);
                    } else {
                        rect.setFill(Color.MOCCASIN);
                    }
                } else {
                    if (columnIndex % 2 == 0) {
                        rect.setFill(Color.MOCCASIN);
                    } else {
                        rect.setFill(Color.SANDYBROWN);
                    }
                }

                gp.add(rect, columnIndex, rowIndex);
            }
        }
    }

    private void setBoard(GridPane gp) {
        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
            // first 4 rows correspond to the black team
            if (rowIndex < 4) {
                setCheckersInRow(gp, rowIndex, Color.BLACK);
            } else if (rowIndex >= 6) {
                setCheckersInRow(gp, rowIndex, Color.WHITE);
            }
        }
    }

    private void setCheckersInRow(GridPane gp, int rowIndex, Color color) {
        for (int columnIndex = 0; columnIndex < SIZE; columnIndex++) {
            Checker checker = new Checker(rowIndex, columnIndex, color);

            if (rowIndex % 2 != 0 && columnIndex % 2 == 0) {
                gp.add(checker, columnIndex, rowIndex);
            }

            if (rowIndex % 2 == 0 && columnIndex % 2 != 0) {
                gp.add(checker, columnIndex, rowIndex);
            }
        }
    }
}
