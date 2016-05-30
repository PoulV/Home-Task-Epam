package com.epam.trenings.utils;

import com.epam.trenings.annotations.InjectCache;

import java.lang.reflect.Field;

/**
 * Created by Pol on 5/28/2016.
 */
public class CacheInjector {
    public static void inject(Object consumer) {
        Class targetClass = consumer.getClass();
        try {
            do {
                for (Field fieldForInjections : targetClass.getDeclaredFields()) {
                    if (fieldForInjections.isAnnotationPresent(InjectCache.class)) {
                        InjectCache cacheForSet = fieldForInjections.getAnnotation(InjectCache.class);
                        fieldForInjections.setAccessible(true);
                        fieldForInjections.set(consumer, CacheLoader.mapOfCaches.get(cacheForSet.name()).newInstance());
                    }
                }
                targetClass = targetClass.getSuperclass();
            } while (!targetClass.equals(Object.class));
        } catch (IllegalAccessException accessException) {
            System.out.println("Illegal access when try inject using custom cache");
            accessException.printStackTrace();
        } catch (InstantiationException instantiationException) {
            System.out.println("Can't initiate injected class");
            instantiationException.printStackTrace();
        }
    }
}
