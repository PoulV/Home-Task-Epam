package com.epam.trenings;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pol on 5/11/2016.
 */
public class Solution {
    private static final int LIST_SIZE = 10_000;

    public static void main(String[] args) {
        List<Integer> testedLinkedList = new LinkedList<>();
        List<Integer> testedArrayList = new ArrayList<>();

        System.out.println("Sequential Addition for list with " + LIST_SIZE + " elements");
        System.out.println("For ArrayList is: " + Utils.sequentialAdditionTest(testedArrayList, LIST_SIZE));
        System.out.println("For LinkedList is: " + Utils.sequentialAdditionTest(testedLinkedList, LIST_SIZE));
        System.out.println();

        System.out.println("Random Addition for list with " + LIST_SIZE + " elements");
        System.out.println("For ArrayList is: " + Utils.randomAdditionTest(testedArrayList, LIST_SIZE));
        System.out.println("For LinkedList is: " + Utils.randomAdditionTest(testedLinkedList, LIST_SIZE));
        System.out.println();

        System.out.println("Random Get for list with " + LIST_SIZE + " elements");
        System.out.println("For ArrayList is: " + Utils.randomGetTest(testedArrayList));
        System.out.println("For LinkedList is: " + Utils.randomGetTest(testedLinkedList));
        System.out.println();

        System.out.println("Iterator for list with " + LIST_SIZE + " elements");
        System.out.println("For ArrayList is: " + Utils.iteratorTest(testedArrayList));
        System.out.println("For LinkedList is: " + Utils.iteratorTest(testedLinkedList));
        System.out.println();

        System.out.println("Sort for list with " + LIST_SIZE + " elements");
        System.out.println("For ArrayList is: " + Utils.sortTest(testedArrayList, LIST_SIZE));
        System.out.println("For LinkedList is: " + Utils.sortTest(testedLinkedList, LIST_SIZE));
        System.out.println();

        System.out.println("Random Remove for list with " + LIST_SIZE + " elements");
        System.out.println("For ArrayList is: " + Utils.randomRemoveTest(testedArrayList));
        System.out.println("For LinkedList is: " + Utils.randomRemoveTest(testedLinkedList));
        System.out.println();
    }
}
