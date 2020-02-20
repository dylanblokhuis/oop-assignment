package com.novi.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dylan Blokhuis
 * @date 12-1-2020
 * Leerlijn: Object Oriented Programmeren
 */
public class Player implements Observable {
    private String name;
    private int score;
    private CheckerType checkerType;
    private List<PlayerObserver> observers;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        observers = new ArrayList<>();
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
