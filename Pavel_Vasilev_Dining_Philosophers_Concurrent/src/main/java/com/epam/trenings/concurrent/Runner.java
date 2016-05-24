package com.epam.trenings.concurrent;

import com.epam.trenings.Utils;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Pol on 5/23/2016.
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
        Utils.threadPool.shutdownNow();
    }
}
