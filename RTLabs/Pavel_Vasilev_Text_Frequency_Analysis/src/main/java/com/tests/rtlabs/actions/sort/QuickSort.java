package com.tests.rtlabs.actions.sort;

import com.tests.rtlabs.actions.io.TextWriter;
import org.apache.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 1 on 01.04.2017.
 */
public class QuickSort extends AbstractSortMap {

    private static final Logger logger = Logger.getLogger(TextWriter.class);

    public Map<String, Integer> sortMap(Map<String, Integer> map4Sort) {
        if ((map4Sort == null) || (map4Sort.isEmpty())) {
            RuntimeException emptyMapException = new RuntimeException("Map for sort is empty!");
            logger.error("Map for sort is empty", emptyMapException);
            throw new RuntimeException("Map for sort is empty!");
        }
        logger.info("Start sort Map by \"quick sort\" method.");

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        Map.Entry<String, Integer>[] entryArray = new Map.Entry[map4Sort.size()];
        map4Sort.entrySet().toArray(entryArray);

        entryArray = quickSort(entryArray);

        for (Map.Entry<String, Integer> entry : entryArray) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        logger.info("Map has been sorted by \"quick sort\" method.");
        return sortedMap;
    }

    public static Map.Entry<String, Integer>[] quickSort(Map.Entry<String, Integer>[] array) {
        quickSort(0, array.length - 1, array);
        return array;
    }

    private static void quickSort(int first, int last, Map.Entry<String, Integer>[] array) {
        int localFist = first;
        int localLast = last;
        if (localFist >= localLast)
            return;
        int current = localFist - (localFist - localLast) / 2;
        while (localFist < localLast) {
            while (localFist < current && (array[localFist].getValue() <= array[current].getValue())) {
                localFist++;
            }
            while (localLast > current && (array[current].getValue() <= array[localLast].getValue())) {
                localLast--;
            }
            if (localFist < localLast) {
                Map.Entry<String, Integer> temp = array[localFist];
                array[localFist] = array[localLast];
                array[localLast] = temp;
                if (localFist == current)
                    current = localLast;
                else if (localLast == current)
                    current = localFist;
            }
        }
        quickSort(first, current, array);
        quickSort(current + 1, last, array);
    }
}
