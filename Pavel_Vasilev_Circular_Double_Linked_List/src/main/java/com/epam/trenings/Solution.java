package com.epam.trenings;

import com.epam.trenings.collection.DoubleLinkedList;
import java.util.ConcurrentModificationException;

public class Solution {
    public static void main(String[] args) {

        DoubleLinkedList<String> myList = new DoubleLinkedList<>();
        myList.add("first");
        myList.add("second");
        myList.add("third");
        myList.add("aaa");

        Utils.printList(myList, "Fisrt creation double linked list:");

        myList.cocktailSort();
        Utils.printList(myList, "Sorted double linked list:");

        Utils.printReversList(myList, "Use reverse iterator");

        myList.remove(0);
        Utils.printList(myList, "Double linked list without first element:");

        myList.add(1, "AAA");
        Utils.printList(myList, "Double linked list with new element:");

        DoubleLinkedList<Integer> myRandomList = Utils.getRandomIntegerList(30);
        myRandomList.cocktailSort();
        Utils.printList(myRandomList, "Sorted random list of Integer:");

        try {
            for (Integer number : myRandomList) {
                myRandomList.add(number + 1);
            }
        } catch (ConcurrentModificationException exception) {
            System.out.println("Catched ConcurrentModificationException!");
        }
        System.out.println();

        DoubleLinkedList<String> myStringList = new DoubleLinkedList<>();
        myStringList.add("1");
        myStringList.add("2");
        myStringList.add("3");

        DoubleLinkedList<Integer> myIntegerList = myStringList.map(new ITypeConverter<String, Integer>() {
            @Override
            public Integer apply(String element) {
                return Integer.parseInt(element);
            }
        });

        System.out.println("Created new DoubleLinkedList with: " + myIntegerList.get(0).getClass());

    }
}
