package com.epam.trenings;

import com.epam.trenings.collection.DoubleLinkedList;

public class TypeConverter<IN extends Comparable<IN>, OUT extends Comparable<OUT>> {
    public OUT aply(IN element) {
        return (OUT) element;
    }

    public DoubleLinkedList<OUT> map(DoubleLinkedList<IN> myList) {
        DoubleLinkedList<OUT> resultList = new DoubleLinkedList<>();

        for (IN elem : myList) {
            resultList.add(aply(elem));
        }

        return resultList;
    }

}
