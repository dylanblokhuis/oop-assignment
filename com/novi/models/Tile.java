package com.novi.models;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author Dylan Blokhuis
 * @date 02/02/2020
 * Leerlijn: Object Oriented Programmeren
 */
public class Tile extends StackPane {
    public static final int SIZE = 45;

    private int x, y;
    private boolean isDark;
    private boolean isAvailable = false;

    public Tile(int x, int y, boolean isDark) {
        this.isDark = isDark;
        setBackground(new Background(
            new BackgroundFill(
                !isDark ? Color.SANDYBROWN : Color.MOCCASIN,
                CornerRadii.EMPTY,
                Insets.EMPTY
            ))
        );

        setPrefWidth(SIZE);
        setPrefHeight(SIZE);
        setAlignment(Pos.CENTER);

        this.x = x;
        this.y = y;
    }

    public boolean isDark() {
        return isDark;
    }

    public int getColumnIndex() {
        return x;
    }

    public int getRowIndex() {
        return y;
    }

    public Checker getChecker() {
        try {
            return (Checker) getChildren().get(0);
        } catch (Exception $e) {
            return null;
        }
    }

    public void setChecker(Checker checker) {
        getChildren().addAll(checker);
    }

    public boolean hasChecker() {
        if (getChildren().isEmpty()) {
            return false;
        }

        return getChecker() != null;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void showAvailability() {
        isAvailable = true;
        Circle circle = new Circle(8, Color.GREEN);
        getChildren().addAll(circle);
    }

    public void removeAvailability() {
        isAvailable = false;
        if (!getChildren().isEmpty()) {
            getChildren().remove(0);
        }
    }
}
