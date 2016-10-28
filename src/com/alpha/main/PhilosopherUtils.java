package com.alpha.main;

import com.alpha.model.Philosopher;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PhilosopherUtils {

    public static List<Philosopher> getListPhilosopher() {
        List<Philosopher> listPhilosopher = new ArrayList<>();
        for (int i = 0; i < Philosopher.PHILOSOPHER_NUM; i++) {
            Philosopher philosopher = new Philosopher(i);
            listPhilosopher.add(philosopher);
        }
        return listPhilosopher;
    }

    public static boolean checkPhilosopherCanEat(int philosopherId, List<Philosopher> listPhilosopher) {
        int leftPhilosopherId = (philosopherId + 4) % 5;
        int rightPhilosopherId = (philosopherId + 1) % 5;
        return !checkPhilosopherIsEating(leftPhilosopherId, listPhilosopher) && !checkPhilosopherIsEating(rightPhilosopherId, listPhilosopher);

    }

    public static Philosopher getPhilosopherById(int id, List<Philosopher> listPhilosopher) {
        for (Philosopher philosopher : listPhilosopher) {
            if (philosopher.getId() == id) {
                return philosopher;
            }
        }
        return null;
    }

    public static int getRandomHungryPhilosopherId() {
        Random random = new Random();
        return (random.nextInt(99) + random.nextInt(91)) % Philosopher.PHILOSOPHER_NUM;
    }
    
    public static boolean isFull() {
        Random random = new Random();
        return 1 == (random.nextInt(99) + random.nextInt(91)) % 2;
    }

    private static boolean checkPhilosopherIsEating(int leftPhilosopherId, List<Philosopher> listPhilosopher) {
        Philosopher philosopher = getPhilosopherById(leftPhilosopherId, listPhilosopher);
        return Philosopher.State.EATING == philosopher.getState();
    }
}
