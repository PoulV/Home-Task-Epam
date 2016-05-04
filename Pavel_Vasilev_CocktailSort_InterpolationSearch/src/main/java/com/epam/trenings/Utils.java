package com.epam.trenings;

import java.util.Random;

/**
 * Created by pava0715 on 04.05.2016.
 */
public class Utils {
    private static Random generator = new Random();

    public static void printArray(int[] array) {
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println(array[length - 1]);
    }

    public static int[] getRandomArray(int length) {
        int[] resultArray = new int[length];
        for (int i = 0; i < length; i++) {
            resultArray[i] = generator.nextInt(100);
        }
        return resultArray;
    }
}
