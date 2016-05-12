package com.epam.trenings;

import com.sun.javafx.collections.MappingChange;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Pol on 5/11/2016.
 */
public class Solution {
    private static final int LIST_SIZE = 10000;

    public static void main(String[] args) {
        /*List<Integer> testedLinkedList = new LinkedList<>();
        List<Integer> testedArrayList = new ArrayList<>();

        System.out.println("Sequential Addition for list with " + LIST_SIZE + " elements");
        System.out.println("ArrayList - " + Utils.sequentialAdditionTest(testedArrayList, LIST_SIZE));
        System.out.println("LinkedList - " + Utils.sequentialAdditionTest(testedLinkedList, LIST_SIZE));
        System.out.println();

        System.out.println("Random Addition for list with " + LIST_SIZE + " elements");
        System.out.println("ArrayList - " + Utils.randomAdditionTest(testedArrayList, LIST_SIZE));
        System.out.println("LinkedList - " + Utils.randomAdditionTest(testedLinkedList, LIST_SIZE));
        System.out.println();

        System.out.println("Random Get for list with " + LIST_SIZE + " elements");
        System.out.println("ArrayList - " + Utils.randomGetTest(testedArrayList));
        System.out.println("LinkedList - " + Utils.randomGetTest(testedLinkedList));
        System.out.println();

        System.out.println("Iterator for list with " + LIST_SIZE + " elements");
        System.out.println("ArrayList - " + Utils.iteratorTest(testedArrayList));
        System.out.println("LinkedList - " + Utils.iteratorTest(testedLinkedList));
        System.out.println();

        System.out.println("Sort for list with " + LIST_SIZE + " elements");
        System.out.println("ArrayList - " + Utils.sortTest(testedArrayList, LIST_SIZE));
        System.out.println("LinkedList - " + Utils.sortTest(testedLinkedList, LIST_SIZE));
        System.out.println();

        System.out.println("Random Remove for list with " + LIST_SIZE + " elements");
        System.out.println("ArrayList - " + Utils.randomRemoveTest(testedArrayList));
        System.out.println("LinkedList - " + Utils.randomRemoveTest(testedLinkedList));
        System.out.println();*/

       /* TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(null);*/

        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(null);

        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(null);

        // map

        Map<Integer, Integer> hashMap = new HashMap<>();
        Map<Integer, Integer> treeMap = new TreeMap<>();
        Map<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<Integer, Integer>();

        fillMap(hashMap);
        fillMap(treeMap);
        fillMap(concurrentHashMap);

      //  nullToMap(concurrentHashMap);
        nullToMap(hashMap);
     //   nullToMap(treeMap);
    }

    static void fillMap (Map<Integer, Integer> map) {
        map.put(1,4);
        map.put(2,5);
        map.put(9,6);
        map.put(6,9);
        map.put(5,8);
        map.put(4,7);
    }
    static void nullToMap (Map<Integer, Integer> map) {
        map.put(null,4);
    }
}
