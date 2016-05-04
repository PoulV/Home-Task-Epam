package com.epam.trenings.actions;

/**
 * Created by Pol on 5/3/2016.
 */
public class CocktailSortAndInterpolationSearch extends AbstractSortAndSearch {

    public int[] sort(int[] array) {
        if ((array == null) || (array.length == 0)) {
            throw new RuntimeException("Array is empty!");
        }
        int left = 0;
        int right = array.length - 1;

        do {
            for (int i = left; i < right; i++) {
                if (array[i] > array[i + 1]) {
                    array[i] ^= array[i + 1];
                    array[i + 1] ^= array[i];
                    array[i] ^= array[i + 1];
                }
            }
            right--;

            for (int i = right; i > left; i--) {
                if (array[i] < array[i - 1]) {
                    array[i] ^= array[i - 1];
                    array[i - 1] ^= array[i];
                    array[i] ^= array[i - 1];
                }
            }
            left++;
        } while (left <= right);

        return array;
    }

    public int search(int[] array, int key) {
        if ((array == null) || (array.length == 0)) {
            throw new RuntimeException("Array is empty!");
        }
        int mid;
        int low = 0;
        int high = array.length - 1;
        int result = -1;

        while ((array[low] < key && array[high] > key) || (array[low] < key || array[high] > key)) {

            mid = low + ((key - array[low]) * (high - low)) / (array[high] - array[low]);
            if (mid > high)
                break;
            if (array[mid] == key) {
                result = mid;
            }

            if (array[mid] < key)
                low = mid + 1;
            else if ((array[mid] >= key) && (mid != 0))
                high = mid - 1;
            else {
                result = mid;
                if (result == 0)
                    break;
            }
        }

        if (result == -1) {
            if (array[low] == key)
                result = low;
            else if (array[high] == key)
                result = high;
        }
        return result;
    }
}
