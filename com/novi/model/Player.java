package com.novi.model;

import java.util.ArrayList;

public class Player {
    private String name;
    public int score;
    public CheckerType checkerType;
    public ArrayList<Checker> captured = new ArrayList<Checker>();

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    public void addScore() {
        this.score++;
    }

    public CheckerType getCheckerType() {
        return checkerType;
    }

    public void setCheckerType(CheckerType checkerType) {
        this.checkerType = checkerType;
    }

    public void addToCaptured(Checker checker) {
        captured.add(checker);
    }

    public ArrayList<Checker> getCaptured() {
        return captured;
    }
}
