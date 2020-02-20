package com.novi.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.InputStream;

/**
 * @author Dylan Blokhuis
 * @date 30-1-2020
 * Leerlijn: Object Oriented Programmeren
 */
public class Checker extends StackPane {
    private CheckerType checkerType;
    private boolean isHighlighted = false;
    private boolean isKing = false;

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

    public void makeKing() {
        isKing = true;

        getChildren().add(kingImage(checkerType));
    }

    private ImageView kingImage(CheckerType checkerType) {
        InputStream inStream = getClass().getResourceAsStream(checkerType == CheckerType.DARK ? "/resources/images/white_crown.png" : "/resources/images/black_crown.png");
        Image imageObject = new Image(inStream);

        return new ImageView(imageObject);
    }

    public boolean isKing() {
        return isKing;
    }
}
