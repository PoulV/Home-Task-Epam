package com.epam.trenings;

import com.epam.trenings.unconcurrent.Fork;
import com.epam.trenings.unconcurrent.Philosopher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pol on 5/22/2016.
 */
public class Utils {
    public static void startThreadList(List<Thread> threadListForStart) {
        for (Thread threadForStart : threadListForStart) {
            threadForStart.start();
        }
    }

    public static List<Fork> initiatedListOfForks(Integer count) {
        List<Fork> resultListOfFork = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            resultListOfFork.add(new Fork("Fork_" + (i + 1)));
        }
        return resultListOfFork;
    }

    public static List<Thread> initiatedListOfPhilosophers(List<Fork> listOfFork) {
        List<Thread> resultListOfPhilosophers = new ArrayList<>();
        Fork leftFork;
        Fork rightFork;

        for (int i = 0; i < listOfFork.size(); i++) {
            leftFork = listOfFork.get(i);
            if ((i + 1) >= listOfFork.size()) {
                rightFork = listOfFork.get(0);
            } else {
                rightFork = listOfFork.get(i + 1);
            }
            resultListOfPhilosophers.add(new Thread(new Philosopher("Philosopher_" + (i + 1), leftFork,rightFork)));
        }

        return resultListOfPhilosophers;
    }

    public static void interruptAll(List<Thread> threadListForInterrupt) {
        for (Thread currentThread : threadListForInterrupt) {
            currentThread.interrupt();
        }
    }
}
