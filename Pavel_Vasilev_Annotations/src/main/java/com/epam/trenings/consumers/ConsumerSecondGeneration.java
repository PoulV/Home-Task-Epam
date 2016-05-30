package com.epam.trenings.consumers;

import com.epam.trenings.annotations.InjectCache;
import com.epam.trenings.caches.DefaultCache;
import com.epam.trenings.caches.ICache;

/**
 * Created by Pol on 5/29/2016.
 */
public class ConsumerSecondGeneration extends ConsumerFirstGeneration {
    @InjectCache(name = "second cache")
    private ICache dailyCache;

    public ConsumerSecondGeneration() {
        dailyCache = new DefaultCache();
    }

    public String getFromSecondCache(int returnIndex) {
        return dailyCache.get(returnIndex);
    }

    public void putInSecondCache(String value) {
        dailyCache.put(value);
    }
}
