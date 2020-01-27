package com.novi.model;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board {
    private static int COLUMN_SIZE = 10;
    private static Color COLUMN_COLOR = new Color(0.1, 0.1, 0.1, 1);

    private int cols, rows;
    private Canvas canvas;

    public Board(int width, int height, Canvas canvas) {
        this.rows = width / COLUMN_SIZE;
        this.cols = height / COLUMN_SIZE;
        this.canvas = canvas;

        draw();
    }

    private void draw() {
        GraphicsContext context = canvas.getGraphicsContext2D();

        context.setFill(COLUMN_COLOR);
        context.fillRect(0, 0, 600, 371);
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
