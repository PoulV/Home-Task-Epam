package com.epam.trenings.caches;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pol on 5/29/2016.
 */
public class DefaultCache implements ICache {
    private List<String> defaultStorage;

    public DefaultCache() {
        defaultStorage = new LinkedList<>();
    }

    @Override
    public void put(String value) {
        defaultStorage.add(value);
    }

    @Override
    public String get(Integer key) {
        return defaultStorage.get(key);
    }
}
