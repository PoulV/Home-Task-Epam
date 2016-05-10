package com.epam.trenings.classes;

/**
 * Created by Pol on 5/9/2016.
 */
public interface IReversIterator<T> {

    boolean hasPrev();

    T prev();

    void remove();
}
