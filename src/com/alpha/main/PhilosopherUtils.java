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
}
