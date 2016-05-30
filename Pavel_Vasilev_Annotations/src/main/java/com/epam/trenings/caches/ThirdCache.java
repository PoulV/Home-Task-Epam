package com.epam.trenings.caches;

import com.epam.trenings.annotations.Cache;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pol on 5/29/2016.
 */
@Cache(name = "third cache")
public class ThirdCache implements ICache {
    private List<String> storage;

    public ThirdCache() {
        storage = new LinkedList<>();
    }

    @Override
    public void put(String value) {
        storage.add(value);
    }

    @Override
    public String get(Integer key) {
        String strResult = storage.get(key) + " from third generation cache";
        return strResult;
    }
}