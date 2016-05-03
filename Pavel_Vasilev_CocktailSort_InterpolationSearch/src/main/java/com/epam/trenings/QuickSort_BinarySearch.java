package com.epam.trenings;

/**
 * Created by Pol on 5/3/2016.
 */
public class QuickSort_BinarySearch extends AbstractSortAndSearch {

    public int[] sort(int[] array) {
        if ((array == null) || (array.length == 0)) {
            throw new RuntimeException("Array is empty!");
        }
        quickSort(0, array.length - 1, array);
        return array;
    }

    private void quickSort(int first, int last, int[] array) {
        int i = first;
        int j = last;
        if (i >= j)
            return;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (array[i] <= array[cur])) {
                i++;
            }
            while (j > cur && (array[cur] <= array[j])) {
                j--;
            }
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        quickSort(first, cur, array);
        quickSort(cur + 1, last, array);
    }

    public int search(int[] array, int key) {
        if ((array == null) || (array.length == 0)) {
            throw new RuntimeException("Array is empty!");
        }
        int first = 0;
        int last = array.length - 1;
        int position;
        int result = -1;

        position = (first + last) / 2;

        while (last - first >= 1) {
            if (array[position] == key) {
                result = position;
            }
            if (array[position] < key) {
                first = position + 1;
            } else {
                last = position;
            }
            position = (first + last) / 2;
        }

        if (result == -1) {
            if (array[position] != key) {
                result = -1;
            } else {
                result = position;
            }
        }
        return result;
    }
}
