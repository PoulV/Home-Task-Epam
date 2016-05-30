package com.epam.trenings.caches;

import com.epam.trenings.annotations.Cache;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pol on 5/29/2016.
 */
@Cache(name = "fourth cache")
public class FourthCache implements ICache {
    private List<String> storage;

    public FourthCache() {
        storage = new LinkedList<>();
    }

    @Override
    public void put(String value) {
        storage.add(value);
    }

    @Override
    public String get(Integer key) {
        String strResult = storage.get(key) + " from fourth generation cache";
        return strResult;
    }
}
