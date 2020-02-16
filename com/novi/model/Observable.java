package com.novi.model;

interface Observable {
    void registerObserver(PlayerObserver observer);

    void notifyObserver();
}
