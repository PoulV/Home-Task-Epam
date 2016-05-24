package com.epam.trenings.unconcurrent;

import com.epam.trenings.Utils;

import java.util.List;

/**
 * Created by Pol on 5/22/2016.
 */
public class Runner {

    public static void runPhilosopherProblem() {
        List<Fork> forkList = Utils.initiatedListOfForks(5);
        List<Thread> philosopherList = Utils.initiatedListOfPhilosophers(forkList);
        Utils.startThreadList(philosopherList);

        try {
            Thread.currentThread().sleep(60 * 1000);
        } catch (InterruptedException e) {
            System.out.println("Main thread of philosopher dinner is interrupted!");
            return;
        }

        Utils.interruptAll(philosopherList);
    }


}
