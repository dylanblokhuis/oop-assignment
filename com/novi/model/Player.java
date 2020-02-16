package com.novi.model;

import java.util.ArrayList;
import java.util.List;

public class Player implements Observable {
    private String name;
    public int score;
    public CheckerType checkerType;
    public ArrayList<Checker> captured = new ArrayList<Checker>();
    private List<PlayerObserver> observers;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        observers = new ArrayList<PlayerObserver>();
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    public void addScore() {
        this.score++;
        this.notifyObserver();
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

    @Override
    public void registerObserver(PlayerObserver observer) {
        if (observer != null) {
            this.observers.add(observer);
        }
    }

    @Override
    public void notifyObserver() {
        for (PlayerObserver observer : observers) {
            observer.update(this);
        }
    }
}
