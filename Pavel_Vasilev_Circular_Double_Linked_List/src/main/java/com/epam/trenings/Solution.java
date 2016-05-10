package com.epam.trenings;

import com.epam.trenings.classes.DoubleLinkedList;
import com.epam.trenings.classes.TypeConverter;

/**
 * Created by Pol on 5/8/2016.
 */
public class Solution {
    public static void main(String[] args) {
        DoubleLinkedList<String> myList = new DoubleLinkedList<>();
        myList.add("first");
        myList.add("second");
        myList.add("third");
        myList.add("aaa");

        Utils.printList(myList, "Fisrt creation double linked list:");

        myList.coctailSort();
        Utils.printList(myList, "Sorted double linked list:");

        Utils.printReversList(myList, "Use reverse iterator");

        myList.remove(0);
        Utils.printList(myList, "Double linked list without first element:");

        myList.add(1, "AAA");
        Utils.printList(myList, "Double linked list with new element:");

        DoubleLinkedList<Integer> myRandomList = Utils.getRandomIntegerList(30);
        myRandomList.coctailSort();
        Utils.printList(myRandomList, "Sorted random list of Integer:");

        DoubleLinkedList<Double> myDoubleList = new TypeConverter<Integer, Double>().map(myRandomList);
    }
}
