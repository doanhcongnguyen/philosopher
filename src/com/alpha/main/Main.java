package com.alpha.main;

import com.alpha.model.Philosopher;
import java.util.List;

public class Main {

    private static final List<Philosopher> LIST_PHILOSPHER;

    static {
        LIST_PHILOSPHER = PhilosopherUtils.getListPhilosopher();
        doPrintPhilosopherState();
    }

    public void doIt() {
        this.doPrintSlash();
        int hungryPhilosopherId = PhilosopherUtils.getRandomHungryPhilosopherId();
        this.doChangeStatusCurrentEatingPhilosopherToThinking(hungryPhilosopherId);
        this.doChangeStatusCurrentHungryPhilosopherToEating();
        this.doNotify(hungryPhilosopherId);
        doPrintPhilosopherState();
    }

    private static void doPrintPhilosopherState() {
        System.out.println("Current state: ");
        LIST_PHILOSPHER.forEach((philo) -> {
            System.out.println("\t- Philosopher <" + philo.getId() + "> is " + philo.getState().toString());
        });
    }

    private void doPrintSlash() {
        System.out.println("====================================================================");
    }

    private void doChangeStatusCurrentEatingPhilosopherToThinking(int hungryPhilosopherId) {
        System.out.println("Notification: ");
        LIST_PHILOSPHER.forEach((philo) -> {
            if (Philosopher.State.EATING == philo.getState() && hungryPhilosopherId != philo.getId()) {
                System.out.println("\t- Philosopher <" + philo.getId() + "> was EATING and now is THINKING");
                philo.setState(Philosopher.State.THINKING);
            }
        });
    }

    private void doChangeStatusCurrentHungryPhilosopherToEating() {
        LIST_PHILOSPHER.forEach((philo) -> {
            if (Philosopher.State.HUNGRY == philo.getState()) {
                System.out.println("\t- Philosopher <" + philo.getId() + "> was HUNGRY and now is EATING");
                philo.setState(Philosopher.State.EATING);
            }
        });
    }

    private void doNotify(int hungryPhilosopherId) {
        Philosopher hungryPhilosopher = PhilosopherUtils.getPhilosopherById(hungryPhilosopherId, LIST_PHILOSPHER);
        switch (hungryPhilosopher.getState()) {
            case EATING:
                System.out.println("\t- Philosopher <" + hungryPhilosopher.getId() + "> stills is HUNGRY. She wants to eat more :)");
                break;
            default:
                System.out.println("\t- Philosopher <" + hungryPhilosopher.getId() + "> is HUNGRY");
                hungryPhilosopher.setState(Philosopher.State.HUNGRY);
                break;
        }
    }
}
