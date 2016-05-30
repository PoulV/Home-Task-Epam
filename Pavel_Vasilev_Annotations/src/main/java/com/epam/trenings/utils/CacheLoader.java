package com.epam.trenings.utils;

import com.epam.trenings.annotations.Cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Pol on 5/28/2016.
 */
public class CacheLoader {
    public static Map<String, Class> mapOfCaches;

    public static void load(String packageName) {
        Set<Class> allClasses = ClassExecutor.getClassesFromPackege(packageName);
        mapOfCaches = new HashMap<>();

        for (Class someClass : allClasses) {
            if (someClass.isAnnotationPresent(Cache.class)) {
                Cache usedCache = (Cache) someClass.getAnnotation(Cache.class);
                mapOfCaches.put(usedCache.name(), someClass);
            }
        }
    }
}
