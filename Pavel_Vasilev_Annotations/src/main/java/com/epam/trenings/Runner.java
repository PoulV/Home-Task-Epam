package com.epam.trenings;

import com.epam.trenings.consumers.ConsumerThirdGeneration;
import com.epam.trenings.utils.CacheInjector;
import com.epam.trenings.utils.CacheLoader;

/**
 * Created by Pol on 5/28/2016.
 */
public class Runner {
    public static void Run() {
        CacheLoader.load("com.epam.trenings.caches");
        ConsumerThirdGeneration consumerThirdGeneration = new ConsumerThirdGeneration();
        CacheInjector.inject(consumerThirdGeneration);

        consumerThirdGeneration.putInFirstCache("first value");
        consumerThirdGeneration.putInSecondCache("second value");
        consumerThirdGeneration.putInThirdCache("third value");
        consumerThirdGeneration.putInFourthCache("fourth value");

        System.out.println(consumerThirdGeneration.getFromFirstCache(0));
        System.out.println(consumerThirdGeneration.getFromSecondCache(0));
        System.out.println(consumerThirdGeneration.getFromThirdCache(0));
        System.out.println(consumerThirdGeneration.getFromFourthCache(0));
    }
}