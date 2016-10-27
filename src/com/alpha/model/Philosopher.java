package com.alpha.model;

public class Philosopher {

    public static final int PHILOSOPHER_NUM = 5;

    private int id;

    private State state = State.THINKING;

    public Philosopher(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public enum State {
        HUNGRY, EATING, THINKING;
    }
}
