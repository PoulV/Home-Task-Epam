package com.epam.trenings.caches;

import com.epam.trenings.annotations.Cache;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pol on 5/28/2016.
 */
@Cache(name = "first cache")
public class FirstCache implements ICache {
    private List<String> storage;

    public FirstCache() {
        storage = new LinkedList<>();
    }

    @Override
    public void put(String value) {
        storage.add(value);
    }

    @Override
    public String get(Integer key) {
        String strResult = storage.get(key) + " from first generation cache";
        return strResult;
    }
}