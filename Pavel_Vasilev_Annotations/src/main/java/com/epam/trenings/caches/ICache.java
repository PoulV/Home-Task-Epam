package com.epam.trenings.caches;

/**
 * Created by Pol on 5/28/2016.
 */
public interface ICache {
    void put(String value);

    String get(Integer key);
}
