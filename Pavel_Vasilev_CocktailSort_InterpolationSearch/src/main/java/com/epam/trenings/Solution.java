package com.epam.trenings;

import com.epam.trenings.actions.AbstractSortAndSearch;
import com.epam.trenings.actions.CocktailSortAndInterpolationSearch;
import com.epam.trenings.actions.QuickSortAndBinarySearch;
import java.util.Random;

/**
 * Created by Pol on 5/3/2016.
 */
public class Solution {
    private static Random generator = new Random();
    private static final int ARRAY_SIZE = 18;

    public static void main(String[] args) {

        AbstractSortAndSearch cocktailSortAndInterpolationSearch = new CocktailSortAndInterpolationSearch();
        AbstractSortAndSearch quickSortAndBinarySearch = new QuickSortAndBinarySearch();
        int[] array = Utils.getRandomArray(ARRAY_SIZE);
        int key = array[generator.nextInt(ARRAY_SIZE)];

        testSortAndSearch(cocktailSortAndInterpolationSearch, array, key);
        testSortAndSearch(quickSortAndBinarySearch, array, key);
    }

    private static void testSortAndSearch(AbstractSortAndSearch sortAndSearch, int[] array, int key) {
        int position;

        System.out.println(sortAndSearch.getClass().toString());
        array = sortAndSearch.sort(array);
        Utils.printArray(array);
        System.out.println("key = " + key);
        position = sortAndSearch.search(array, key);
        System.out.println("Position key = " + position);
        System.out.println();
    }
}
