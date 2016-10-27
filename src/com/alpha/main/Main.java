package com.alpha.main;

import com.alpha.model.Philosopher;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final int TIME_TO_SLEEP = 1000;
    private static final List<Philosopher> LIST_PHILOSPHER;

    static {
        LIST_PHILOSPHER = PhilosopherUtils.getListPhilosopher();
        doPrintPhilosopherState();
    }

    public void doIt() throws InterruptedException {
        this.doPrintSlash();
        List<Integer> hungryPhilosopherId = Arrays.asList(PhilosopherUtils.getRandomHungryPhilosopherId(), PhilosopherUtils.getRandomHungryPhilosopherId());
        this.doChangeStatusCurrentEatingPhilosopherToThinking(hungryPhilosopherId);
        this.doChangeStatusCurrentHungryPhilosopherToEating();
        this.doNotify(hungryPhilosopherId);
        doPrintPhilosopherState();
    }

    private static void doPrintPhilosopherState() {
        doPrintMessage("Current state: ");
        LIST_PHILOSPHER.forEach((philo) -> {
            doPrintMessage("\t- Philosopher <" + philo.getId() + "> is " + philo.getState().toString());
        });
    }

    private void doPrintSlash() throws InterruptedException {
        System.out.println("====================================================================");
        doPrintMessage("Notification: ");
    }

    private void doChangeStatusCurrentEatingPhilosopherToThinking(List<Integer> hungryPhilosopherId) {
        hungryPhilosopherId.forEach((id) -> {
            this.doChangeStatusCurrentEatingPhilosopherToThinking(id);
        });
    }

    private void doChangeStatusCurrentEatingPhilosopherToThinking(int hungryPhilosopherId) {
        LIST_PHILOSPHER.forEach((philo) -> {
            if (Philosopher.State.EATING == philo.getState() && hungryPhilosopherId != philo.getId()) {
                doPrintMessage("\t- Philosopher <" + philo.getId() + "> was EATING and now is THINKING");
                philo.setState(Philosopher.State.THINKING);
            }
        });
    }

    private void doChangeStatusCurrentHungryPhilosopherToEating() {
        LIST_PHILOSPHER.forEach((philo) -> {
            if (Philosopher.State.HUNGRY == philo.getState()) {
                if (PhilosopherUtils.checkPhilosopherCanEat(philo.getId(), LIST_PHILOSPHER)) {
                    doPrintMessage("\t- Philosopher <" + philo.getId() + "> was HUNGRY and now is EATING");
                    philo.setState(Philosopher.State.EATING);
                } else {
                    doPrintMessage("\t- Philosopher <" + philo.getId() + "> was HUNGRY but she cannot EATING, beacause of left or right one is eating");
                }
            }
        });
    }

    private void doNotify(List<Integer> hungryPhilosopherId) {
        hungryPhilosopherId.forEach((id) -> {
            this.doNotify(id);
        });
    }

    private void doNotify(int hungryPhilosopherId) {
        Philosopher hungryPhilosopher = PhilosopherUtils.getPhilosopherById(hungryPhilosopherId, LIST_PHILOSPHER);
        switch (hungryPhilosopher.getState()) {
            case EATING:
                doPrintMessage("\t- Philosopher <" + hungryPhilosopher.getId() + "> stills is HUNGRY. She wants to eat more :)");
                break;
            default:
                doPrintMessage("\t- Philosopher <" + hungryPhilosopher.getId() + "> is HUNGRY");
                hungryPhilosopher.setState(Philosopher.State.HUNGRY);
                break;
        }
    }

    private static void doPrintMessage(String message) {
        System.out.println(message);
        try {
            Thread.sleep(TIME_TO_SLEEP);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
