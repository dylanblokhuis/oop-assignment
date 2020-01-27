package com.novi.model;

public class GameLoop implements Runnable {
    private static int FRAME_RATE = 20;
    private float interval = 1000.0f / FRAME_RATE;

    private boolean running = false;
    private Thread thread;

    public GameLoop() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        running = true;


        while (running) {

        }
    }
}
