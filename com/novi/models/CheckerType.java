package com.novi.models;


import javafx.scene.paint.Color;

/**
 * @author Dylan Blokhuis
 * @date 9-2-2020
 * Leerlijn: Object Oriented Programmeren
 */
public enum CheckerType {
    DARK, CLEAR;

    public static Color getColorFromType(CheckerType checkerType) {
        return checkerType == CLEAR ? Color.WHITE : Color.BLACK;
    }
}
