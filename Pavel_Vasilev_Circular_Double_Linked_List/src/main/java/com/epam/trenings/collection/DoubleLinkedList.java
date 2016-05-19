package com.epam.trenings.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class DoubleLinkedList<T extends Comparable<T>> implements Iterable<T>, IReversIterable<T> {
    private int size = 0;
    private Node<T> first;
    private Node<T> last;
    private Integer countOfModification = 0;

    public T get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    public T set(int index, T element) {
        checkElementIndex(index);
        countOfModification++;
        Node<T> x = node(index);
        T oldVal = x.item;
        x.item = element;
        return oldVal;
    }

    public boolean add(T t) {
        countOfModification++;
        final Node<T> locLast = last;
        final Node<T> newNode = new Node<>(locLast, t, null);
        last = newNode;
        if (locLast == null) {
            first = newNode;
            newNode.prev = newNode;
        } else {
            locLast.next = newNode;
            newNode.prev = locLast;
        }
        newNode.next = first;
        first.prev = last;

        size++;
        return true;
    }

    Node<T> node(int index) {
        if (index < (size >> 1)) {
            Node<T> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<T> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    public T remove(int index) {
        countOfModification++;
        checkElementIndex(index);
        return clearLink(node(index));
    }

    private T clearLink(Node<T> nodeForDelete) {
        final T element = nodeForDelete.item;
        final Node<T> next = nodeForDelete.next;
        final Node<T> prev = nodeForDelete.prev;

        if (prev == last) {
            first = next;
        } else {
            prev.next = next;
            nodeForDelete.prev = null;
        }

        if (next == first) {
            last = prev;
        } else {
            next.prev = prev;
            nodeForDelete.next = null;
        }

        nodeForDelete.item = null;
        size--;
        return element;
    }


    void linkBefore(T valueForInsert, Node<T> targetNode) {
        countOfModification++;
        final Node<T> prev = targetNode.prev;
        final Node<T> locLast = last;
        final Node<T> newNode = new Node<>(prev, valueForInsert, targetNode);
        targetNode.prev = newNode;
        if (prev == null) {
            first = newNode;
            newNode.prev = locLast;
        } else
            prev.next = newNode;
        size++;
    }

    public void add(int index, T element) {
        checkElementIndex(index);

        if (index == size)
            add(element);
        else
            linkBefore(element, node(index));
    }

    public int getSize() {
        return size;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }


    public void coctailSort() {
        countOfModification++;
        if (size == 0) {
            throw new RuntimeException("List is empty!");
        }
        int left = 0;
        int right = size - 1;
        T localTemp;

        do {
            for (int i = left; i < right; i++) {
                if (get(i).compareTo(get(i + 1)) > 0) {
                    localTemp = get(i);
                    set(i, get(i + 1));
                    set(i + 1, localTemp);
                }
            }
            right--;

            for (int i = right; i > left; i--) {
                if (get(i).compareTo(get(i - 1)) < 0) {
                    localTemp = get(i);
                    set(i, get(i - 1));
                    set(i - 1, localTemp);
                }
            }
            left++;
        } while (left <= right);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyItr(0);
    }

    @Override
    public IReversIterator<T> reversIterator() {
        return new MyItr(size - 1);
    }

    private class MyItr implements Iterator<T>, IReversIterator<T> {
        private Node<T> lastReturned = null;
        private Node<T> next;
        private Node<T> prev;
        private int nextIndex;
        private int prevIndex = size;
        private Integer exceptableCountModification = countOfModification;


        MyItr(int startIndex) {
            next = (startIndex == size) ? node(0) : node(startIndex);
            prev = (startIndex == 0) ? node(size - 1) : node(startIndex);
            nextIndex = startIndex;
        }

        private void checkModification() {
            if (exceptableCountModification != countOfModification)
                throw new ConcurrentModificationException();
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public T next() {
            checkModification();
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        @Override
        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();

            Node<T> lastNext = lastReturned.next;
            clearLink(lastReturned);
            if (next == lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = null;
        }

        @Override
        public boolean hasPrev() {
            return prevIndex > 0;
        }

        @Override
        public T prev() {
            checkModification();
            lastReturned = prev;
            prev = prev.prev;
            prevIndex--;
            return lastReturned.item;
        }
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }


}