package com.epam.trenings.classes;


/**
 * Created by Pol on 5/10/2016.
 */
public class TypeConverter<T extends Comparable<T>, T1 extends Comparable<T1>> {
    public T1 aply(T element) {
        return (T1) element;
    }

    public DoubleLinkedList<T1> map(DoubleLinkedList<T> myList) {
        DoubleLinkedList<T1> resultList = new DoubleLinkedList<>();

        for (T elem : myList) {
            resultList.add(aply(elem));
        }

        return resultList;
    }

}
