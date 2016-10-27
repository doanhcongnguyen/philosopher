package com.alpha.scheduler;

import java.util.Timer;

public class SchedulerMain {

    private static final long PERIOD = 3000;

    public static void main(String[] args) {
        new Timer().schedule(new SchedulerTask(), 0, PERIOD);
    }
}
