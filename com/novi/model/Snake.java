package com.novi.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Snake {
    public int x = 0;
    public int y = 0;
    public int speed = 1;
    private static Color COLOR = new Color(1,1,1, 1);

    public void update() {
        this.x = this.x + this.speed;
        this.y = this.y + this.speed;
    }

    public void show(Board board) {
        GraphicsContext context = board.getCanvas().getGraphicsContext2D();
        context.setFill(COLOR);
        context.fillRect(this.x, this.y, 10,10);
    }
}
