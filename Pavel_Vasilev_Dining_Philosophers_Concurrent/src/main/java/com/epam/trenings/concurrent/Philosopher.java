package com.epam.trenings.concurrent;

/**
 * Created by Pol on 5/23/2016.
 */
public class Philosopher implements Runnable {
    private Fork leftFork;
    private Fork rightFork;
    private String name;


    public Philosopher(String name, Fork leftFork, Fork rightFork) {
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (rightFork.get(this.name)) {
                    if (leftFork.get(this.name)) {
                        System.out.println(name + " eating delicious ...");
                        Thread.currentThread().sleep(200);
                        System.out.println(name + " eating done, and star thinking...");
                        leftFork.put(this.name);
                    }
                    rightFork.put(this.name);
                }
                Thread.currentThread().sleep(100);
            } catch (InterruptedException interruptedException) {
                System.out.println("Thread for " + name + " has been interrupted!");
                break;
            }
        }
    }
}
