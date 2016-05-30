package com.epam.trenings.caches;

import com.epam.trenings.annotations.Cache;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pol on 5/28/2016.
 */
@Cache(name = "second cache")
public class SecondCache implements ICache {
    private List<String> storage;

    public SecondCache() {
        storage = new ArrayList<>();
    }

    @Override
    public void put(String value) {
        storage.add(value);
    }

    @Override
    public String get(Integer key) {
        String strResult = storage.get(key) + " from second generation cache";
        return strResult;
    }
}