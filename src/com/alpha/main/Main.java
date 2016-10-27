package com.alpha.main;

import com.alpha.model.Philosopher;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static final List<Philosopher> LIST_PHILOSPHER;

    static {
        LIST_PHILOSPHER = getListPhilosopher();
        doPrintPhilosopherState();
    }

    public void doIt() {
        this.doPrintSlash();
        int hungryPhilosopherId = this.getRandomHungryPhilosopherId();
        // TODO: print philosopher who changed from eating to thinking
        this.doChangeStatusCurrentEatingPhilosopherToThinking(hungryPhilosopherId);
        this.doChangeStatusCurrentHungryPhilosopherToEating();
        this.doNotify(hungryPhilosopherId);
        doPrintPhilosopherState();
    }

    public Philosopher getPhilosopherById(int id, List<Philosopher> listPhilosopher) {
        for (Philosopher philosopher : listPhilosopher) {
            if (philosopher.getId() == id) {
                return philosopher;
            }
        }
        return null;
    }

    public static List<Philosopher> getListPhilosopher() {
        List<Philosopher> listPhilosopher = new ArrayList<>();
        for (int i = 0; i < Philosopher.PHILOSOPHER_NUM; i++) {
            Philosopher philosopher = new Philosopher(i);
            listPhilosopher.add(philosopher);
        }
        return listPhilosopher;
    }

    public int getRandomHungryPhilosopherId() {
        Random random = new Random();
        return (random.nextInt(99) + random.nextInt(91)) % Philosopher.PHILOSOPHER_NUM;
    }

    private static void doPrintPhilosopherState() {
        LIST_PHILOSPHER.forEach((philo) -> {
            System.out.println("Philosopher <" + philo.getId() + "> is " + philo.getState().toString());
        });
    }

    private void doPrintSlash() {
        System.out.println("==================================");
    }

    private void doChangeStatusCurrentHungryPhilosopherToEating() {
        LIST_PHILOSPHER.forEach((philo) -> {
            if (Philosopher.State.HUNGRY == philo.getState()) {
                philo.setState(Philosopher.State.EATING);
            }
        });
    }

    private void doChangeStatusCurrentEatingPhilosopherToThinking(int hungryPhilosopherId) {
        LIST_PHILOSPHER.forEach((philo) -> {
            if (Philosopher.State.EATING == philo.getState() && hungryPhilosopherId != philo.getId()) {
                philo.setState(Philosopher.State.THINKING);
            }
        });
    }

    private void doNotify(int hungryPhilosopherId) {
        Philosopher hungryPhilosopher = this.getPhilosopherById(hungryPhilosopherId, LIST_PHILOSPHER);
        switch (hungryPhilosopher.getState()) {
            case EATING:
                System.out.println("Notification: philosopher <" + hungryPhilosopher.getId() + "> stills is hungry");
                break;
            default:
                System.out.println("Notification: philosopher <" + hungryPhilosopher.getId() + "> is hungry");
                hungryPhilosopher.setState(Philosopher.State.HUNGRY);
                break;
        }
    }
}
