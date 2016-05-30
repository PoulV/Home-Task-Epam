package com.epam.trenings.consumers;

import com.epam.trenings.annotations.InjectCache;
import com.epam.trenings.caches.DefaultCache;
import com.epam.trenings.caches.ICache;

/**
 * Created by Pol on 5/29/2016.
 */
public class ConsumerFirstGeneration {
    @InjectCache(name = "first cache")
    private ICache dailyCache;

    public ConsumerFirstGeneration() {
        dailyCache = new DefaultCache();
    }

    public String getFromFirstCache(int returnIndex) {
        return dailyCache.get(returnIndex);
    }

    public void putInFirstCache(String value) {
        dailyCache.put(value);
    }
}
