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
                System.out.println("\t- Philosopher <" + philo.getId() + "> was eating and now is thinking");
                philo.setState(Philosopher.State.THINKING);
            }
        });
    }

    private void doChangeStatusCurrentHungryPhilosopherToEating() {
        LIST_PHILOSPHER.forEach((philo) -> {
            if (Philosopher.State.HUNGRY == philo.getState()) {
                System.out.println("\t- Philosopher <" + philo.getId() + "> was hungry and now is eating");
                philo.setState(Philosopher.State.EATING);
            }
        });
    }

    private void doNotify(int hungryPhilosopherId) {
        Philosopher hungryPhilosopher = this.getPhilosopherById(hungryPhilosopherId, LIST_PHILOSPHER);
        switch (hungryPhilosopher.getState()) {
            case EATING:
                System.out.println("\t- Philosopher <" + hungryPhilosopher.getId() + "> stills is hungry. She wants to eat more :)");
                break;
            default:
                System.out.println("\t- Philosopher <" + hungryPhilosopher.getId() + "> is hungry");
                hungryPhilosopher.setState(Philosopher.State.HUNGRY);
                break;
        }
    }
}
