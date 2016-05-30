package com.epam.trenings.consumers;

import com.epam.trenings.annotations.InjectCache;
import com.epam.trenings.caches.DefaultCache;
import com.epam.trenings.caches.ICache;

/**
 * Created by Pol on 5/29/2016.
 */
public class ConsumerThirdGeneration extends ConsumerSecondGeneration {
    @InjectCache(name = "third cache")
    private ICache dailyCache;

    @InjectCache(name = "fourth cache")
    private ICache cpecialCache;

    public ConsumerThirdGeneration() {
        dailyCache = new DefaultCache();
        cpecialCache = new DefaultCache();
    }

    public String getFromThirdCache(int returnIndex) {
        return dailyCache.get(returnIndex);
    }

    public void putInThirdCache(String value) {
        dailyCache.put(value);
    }

    public String getFromFourthCache(int returnIndex) {
        return cpecialCache.get(returnIndex);
    }

    public void putInFourthCache(String value) {
        cpecialCache.put(value);
    }
}
