package com.epam.trenings;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Pol on 5/11/2016.
 */
public class Utils {
    private static Random generator = new Random();

    public static long sequentialAdditionTest(List<Integer> testedList, int count) {
        testedList.clear();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            testedList.add(1);
        }
        long result = System.currentTimeMillis() - startTime;
        return result;
    }

    public static long randomAdditionTest(List<Integer> testedList, int count) {
        testedList.clear();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < count-1; i++) {
            testedList.add(generator.nextInt(i+1), 1);
        }
        long result = System.currentTimeMillis() - startTime;
        return result;
    }

    public static long randomGetTest(List<Integer> testedList) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < testedList.size(); i++) {
            testedList.get(generator.nextInt(testedList.size()));
        }
        long result = System.currentTimeMillis() - startTime;
        return result;
    }

    public static long iteratorTest(List<Integer> testedList) {
        long startTime = System.currentTimeMillis();
        for (Integer num : testedList) {
            num.intValue();
        }
        long result = System.currentTimeMillis() - startTime;
        return result;
    }

    public static long sortTest(List<Integer> testedList, int count) {
        testedList.clear();
        Collections.addAll(testedList, getRandomArray(count));
        long startTime = System.currentTimeMillis();
        Collections.sort(testedList);
        long result = System.currentTimeMillis() - startTime;
        return result;
    }

    public static long randomRemoveTest(List<Integer> testedList) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < testedList.size(); i++) {
            testedList.remove(generator.nextInt(testedList.size() - 1));
        }
        long result = System.currentTimeMillis() - startTime;
        return result;
    }

    public static Integer[] getRandomArray(int length) {
        Integer[] resultArray = new Integer[length];
        for (int i = 0; i < length; i++) {
            resultArray[i] = generator.nextInt(100);
        }
        return resultArray;
    }
}
