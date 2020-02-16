package com.novi.model;

/**
 * @author Dylan Blokhuis
 * @date 16-2-2020
 * Leerlijn: Object Oriented Programmeren
 */
interface Observable {
    void registerObserver(PlayerObserver observer);

    void notifyObserver();
}
