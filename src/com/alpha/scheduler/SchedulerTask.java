package com.alpha.scheduler;

import com.alpha.main.Main;
import java.util.TimerTask;

public class SchedulerTask extends TimerTask {
    
    private final Main main = new Main();

    @Override
    public void run() {
        main.doIt();
    }
}
