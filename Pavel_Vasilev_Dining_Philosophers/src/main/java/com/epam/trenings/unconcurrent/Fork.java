package com.epam.trenings.unconcurrent;

/**
 * Created by Pol on 5/22/2016.
 */
public class Fork {
    private String name;
    public volatile Boolean availableStatus;
    private final Boolean AVAILABLE = true;
    private final Boolean NOT_AVAILABLE = false;


    public Fork(String name) {
        this.name = name;
        this.availableStatus = true;
    }

    public void put(String whoPut) {
        this.availableStatus = AVAILABLE;
        System.out.println(whoPut + " putting " + this.name);
    }

    public boolean get(String whoGet) {
        if (this.availableStatus == AVAILABLE) {
            this.availableStatus = NOT_AVAILABLE;
            System.out.println(whoGet + " getting " + this.name);
            return true;
        }
        put(whoGet);
        return false;
    }
}
