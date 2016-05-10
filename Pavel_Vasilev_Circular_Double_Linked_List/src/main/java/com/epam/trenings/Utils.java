package com.epam.trenings;

import com.epam.trenings.classes.IReversIterator;
import com.epam.trenings.classes.DoubleLinkedList;

import java.util.Random;

/**
 * Created by pava0715 on 04.05.2016.
 */
public class Utils {
    private static Random generator = new Random();

    public static void printList(DoubleLinkedList<?> myList) {
        printList(myList, null);
    }

    public static void printList(DoubleLinkedList<?> myList, String header) {
        if (header != null)
            System.out.println(header);
        for (int i = 0; i < myList.getSize(); i++) {
            System.out.print(myList.get(i) + ", ");
        }
        System.out.println();
        System.out.println();
    }

    public static void printReversList(DoubleLinkedList<?> myList) {
        printReversList(myList, null);
    }

    public static void printReversList(DoubleLinkedList<?> myList, String header) {
        if (header != null)
            System.out.println(header);
        IReversIterator reversIterator = myList.reversIterator();
        while (reversIterator.hasPrev()) {
            System.out.print(reversIterator.prev() + ",");
        }
        System.out.println();
        System.out.println();
    }

    public static DoubleLinkedList getRandomIntegerList(int length) {
        DoubleLinkedList<Integer> resultList = new DoubleLinkedList<>();
        for (int i = 0; i < length; i++) {
            resultList.add(generator.nextInt(100));
        }
        return resultList;
    }
}
