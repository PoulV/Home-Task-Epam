package com.epam.trenings;

/**
 * Created by Pol on 4/27/2016.
 */
public class PavelVasilevQuickSortAndBinarySearch {

    public static void main(String[] args) {
        int[] array = {3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 6, 34, 45, 45, 45, 45, 56, 56, 345};
        int key = 45;
        int position;

        array = quickSort(array);
        printArray(array);
        System.out.println("key = " + key);
        position = binarySearch(array, key);
        System.out.println("Position key = " + position);
    }

    public static void printArray(int[] array) {
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println(array[length - 1]);
    }
    public static int[] quickSort(int[] array) {
        if ((array == null) || (array.length == 0)) {
            throw new RuntimeException("Array is empty!");
        }
        quickSort(0, array.length - 1, array);
        return array;
    }

    private static void quickSort(int first, int last, int[] array) {
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

    public static int binarySearch(int[] array, int key) {
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