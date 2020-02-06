package com.novi.model;

import com.novi.controller.CheckersController;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author Dylan Blokhuis
 * @date 30-1-2020
 * Leerlijn: Object Oriented Programmeren
 */
public class Checker extends StackPane {
    private int oldX, oldY;
    private double mouseX, mouseY;

    public Checker(int x, int y, Color color) {
        Circle stone = new Circle(15, color);
        move(x, y);

        // center Checker inside tile
        setTranslateX(Tile.SIZE / 6.4);
        setTranslateY(Tile.SIZE / 5.5);

        addListeners();

        getChildren().add(stone);
    }

    private void addListeners() {
        setOnMousePressed(event -> {
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
        });

        setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - mouseX;
            double deltaY = event.getSceneY() - mouseY;

            relocate( deltaX + oldX, deltaY + oldY);
        });
    }

    public void move(int x, int y) {
        oldX = x * Tile.SIZE;
        oldY =  y * Tile.SIZE;
        relocate(oldX, oldY);
    }

    public void toOldPosition() {
        relocate(oldX, oldY);
    }
}
