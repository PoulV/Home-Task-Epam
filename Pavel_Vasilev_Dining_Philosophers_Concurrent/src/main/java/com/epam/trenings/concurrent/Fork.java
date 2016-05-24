package com.epam.trenings.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Pol on 5/23/2016.
 */
public class Fork extends ReentrantLock{
    private String name;

    public Fork(String name) {
        this.name = name;
    }

    public synchronized void put(String whoPut) {
        unlock();
        System.out.println(whoPut + " put " + this.name);
    }

    public synchronized boolean get(String whoGet) {
        System.out.println(whoGet + " get " + this.name);
        return tryLock();
    }



}
