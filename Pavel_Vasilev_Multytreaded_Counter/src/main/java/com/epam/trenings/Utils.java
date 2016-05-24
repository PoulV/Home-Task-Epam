package com.epam.trenings;

import java.util.List;

/**
 * Created by Pol on 5/23/2016.
 */
public class Utils {


    public static void startThreadList(List<Thread> threadListForStart) {
        for (Thread threadForStart : threadListForStart) {
            threadForStart.start();
        }
    }

    public static void massJoinForThreadList(List<Thread> targetThreadList) {
        for (Thread runnable : targetThreadList) {
            try {
                runnable.join();
            } catch (InterruptedException interruptedException) {
                System.out.println("Thread has been interrupted!");
                interruptedException.printStackTrace();
            }
        }
    }
}
