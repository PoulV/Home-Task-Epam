package com.epam.trenings;

import com.epam.trenings.collection.DoubleLinkedList;
import com.epam.trenings.collection.IReversIterator;
import java.util.Random;

public class Utils {
    private static Random generator = new Random();

    public static void printList(DoubleLinkedList<?> myList, String header) {
        if (myList == null)
            throw new NullPointerException("Can't print null list");
        if (header != null)
            System.out.println(header);
        for (int i = 0; i < myList.getSize(); i++) {
            System.out.print(myList.get(i) + ", ");
        }
        System.out.println();
        System.out.println();
    }

    public static void printReversList(DoubleLinkedList<?> myList, String header) {
        if (myList == null)
            throw new NullPointerException("Can't print null list");
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
        if (length <= 0)
            throw new IllegalArgumentException("Can't create zero-length random list.");
        DoubleLinkedList<Integer> resultList = new DoubleLinkedList<>();
        for (int i = 0; i < length; i++) {
            resultList.add(generator.nextInt(100));
        }
        return resultList;
    }
}
