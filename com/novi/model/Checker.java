package com.novi.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author Dylan Blokhuis
 * @date 30-1-2020
 * Leerlijn: Object Oriented Programmeren
 */
public class Checker extends Circle {
    private int oldX, oldY;
    private double mouseX, mouseY;

    public Checker(int x, int y, Color color) {
        super(15, color);

        // center Checker inside tile
        setTranslateX(Tile.SIZE / 6.0);
        setTranslateY(Tile.SIZE / 5.5);
        relocate(x * Tile.SIZE, y * Tile.SIZE);

        addListeners();
    }

    private void addListeners() {
        setOnMousePressed(event -> {
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();

            System.out.println(mouseX);
        });

        setOnMouseDragged(event -> {
            relocate(event.getSceneX() - mouseX + oldX, event.getSceneY() - mouseY + oldY);
        });

        setOnMouseReleased(event -> {
            // figure out move logic
        });
    }

    public boolean move(int x, int y) {
        oldX = x * Tile.SIZE;
        oldY =  y * Tile.SIZE;
        relocate(oldX, oldY);

        return true;
    }
}
