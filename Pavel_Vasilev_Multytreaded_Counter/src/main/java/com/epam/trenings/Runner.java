package com.epam.trenings;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Pol on 5/22/2016.
 */
public class Runner {
    private static AtomicInteger counter;

    public static void runMultythreadedCounter() {
        counter = new AtomicInteger();

        List<Thread> runnableList = initiateThreadList();
        Utils.startThreadList(runnableList);
        Utils.massJoinForThreadList(runnableList);

        System.out.println(counter);
    }

    public static List<Thread> initiateThreadList() {
        List<Thread> resultThreadList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            resultThreadList.add(new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 100_000; j++) {
                        counter.incrementAndGet();

                    }
                }
            });
        }
        return resultThreadList;
    }
}
