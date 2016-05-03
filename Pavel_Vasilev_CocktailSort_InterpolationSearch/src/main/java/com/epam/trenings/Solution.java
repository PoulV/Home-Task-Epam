package com.epam.trenings;

import java.util.Random;

/**
 * Created by Pol on 5/3/2016.
 */
public class Solution {
    private static Random generator = new Random();
    private static final int ARRAY_SIZE = 18;

    public static void main(String[] args) {
        int[] array = getRandomArray(ARRAY_SIZE);
        int key = array[generator.nextInt(ARRAY_SIZE)];

        int position;
        AbstractSortAndSearch cocktailSort_interpolationSearch = new CocktailSort_InterpolationSearch();
        AbstractSortAndSearch quickSort_binarySearch = new QuickSort_BinarySearch();

        System.out.println("CocktailSort_InterpolationSearch");
        array = cocktailSort_interpolationSearch.sort(array);
        printArray(array);
        System.out.println("key = " + key);
        position = cocktailSort_interpolationSearch.search(array, key);
        System.out.println("Position key = " + position);
        System.out.println();

        System.out.println("QuickSort_BinarySearch");
        array = quickSort_binarySearch.sort(array);
        printArray(array);
        System.out.println("key = " + key);
        position = quickSort_binarySearch.search(array, key);
        System.out.println("Position key = " + position);
    }

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
