package com.novi.models;


import javafx.scene.paint.Color;

public enum CheckerType {
    DARK, CLEAR;

    public static Color getColorFromType(CheckerType checkerType) {
        return checkerType == CLEAR ? Color.WHITE : Color.BLACK;
    }
}
