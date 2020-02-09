package com.novi.model;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author Dylan Blokhuis
 * @date 30-1-2020
 * Leerlijn: Object Oriented Programmeren
 */
public class Checker extends StackPane {
    private CheckerType checkerType;
    private boolean isHighlighted = false;

    public Checker(CheckerType checkerType) {
        this.checkerType = checkerType;
        Circle stone = new Circle(15, CheckerType.getColorFromType(checkerType));
        getChildren().add(stone);
    }

    public CheckerType getCheckerType() {
        return checkerType;
    }

    public int getColumnIndex() {
        Tile tile = (Tile) getParent();
        return tile.getColumnIndex();
    }

    public int getRowIndex() {
        Tile tile = (Tile) getParent();
        return tile.getRowIndex();
    }

    public boolean isHighlighted() {
        return isHighlighted;
    }

    public void highlight() {
        isHighlighted = true;
        Circle stone = (Circle) getChildren().get(0);
        stone.setStrokeWidth(5);
        stone.setStroke(Color.YELLOW);
    }

    public void removeHighlight() {
        isHighlighted = false;
        Circle stone = (Circle) getChildren().get(0);
        stone.setStrokeWidth(0);
    }

    public void selected() {
        Circle stone = (Circle) getChildren().get(0);
        stone.setStrokeWidth(2);
        stone.setStroke(getCheckerType() == CheckerType.DARK ? Color.WHITE : Color.BLACK);
    }

    public void deselect() {
        Circle stone = (Circle) getChildren().get(0);
        if (isHighlighted()) {
            highlight();
        } else {
            stone.setStrokeWidth(0);
        }
    }
}
