package com.epam.trenings.collection;

import com.epam.trenings.ITypeConverter;
import sun.org.mozilla.javascript.internal.Function;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class DoubleLinkedList<GENERAL_TYPE extends Comparable<GENERAL_TYPE>> implements Iterable<GENERAL_TYPE>, IReversIterable<GENERAL_TYPE> {
    private int size = 0;
    private Node<GENERAL_TYPE> first;
    private Node<GENERAL_TYPE> last;
    private Integer countOfModification = 0;

    public GENERAL_TYPE get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    public GENERAL_TYPE set(int index, GENERAL_TYPE element) {
        checkElementIndex(index);
        countOfModification++;
        Node<GENERAL_TYPE> x = node(index);
        GENERAL_TYPE oldVal = x.item;
        x.item = element;
        return oldVal;
    }

    public boolean add(GENERAL_TYPE newValue) {
        countOfModification++;
        final Node<GENERAL_TYPE> locLast = last;
        final Node<GENERAL_TYPE> newNode = new Node<>(locLast, newValue, null);
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

    Node<GENERAL_TYPE> node(int index) {
        if (index < (size >> 1)) {
            Node<GENERAL_TYPE> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<GENERAL_TYPE> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    public GENERAL_TYPE remove(int index) {
        countOfModification++;
        checkElementIndex(index);
        return clearLink(node(index));
    }

    private GENERAL_TYPE clearLink(Node<GENERAL_TYPE> nodeForDelete) {
        final GENERAL_TYPE element = nodeForDelete.item;
        final Node<GENERAL_TYPE> next = nodeForDelete.next;
        final Node<GENERAL_TYPE> prev = nodeForDelete.prev;

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


    void linkBefore(GENERAL_TYPE valueForInsert, Node<GENERAL_TYPE> targetNode) {
        countOfModification++;
        final Node<GENERAL_TYPE> prev = targetNode.prev;
        final Node<GENERAL_TYPE> locLast = last;
        final Node<GENERAL_TYPE> newNode = new Node<>(prev, valueForInsert, targetNode);
        targetNode.prev = newNode;
        if (prev == null) {
            first = newNode;
            newNode.prev = locLast;
        } else
            prev.next = newNode;
        size++;
    }

    public void add(int index, GENERAL_TYPE element) {
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


    public void cocktailSort() {
        countOfModification++;
        if (size == 0) {
            throw new RuntimeException("List is empty!");
        }
        int left = 0;
        int right = size - 1;
        GENERAL_TYPE localTemp;

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
    public Iterator<GENERAL_TYPE> iterator() {
        return new MyItr(0);
    }

    @Override
    public IReversIterator<GENERAL_TYPE> reversIterator() {
        return new MyItr(size - 1);
    }

    private class MyItr implements Iterator<GENERAL_TYPE>, IReversIterator<GENERAL_TYPE> {
        private Node<GENERAL_TYPE> lastReturned = null;
        private Node<GENERAL_TYPE> next;
        private Node<GENERAL_TYPE> prev;
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
        public GENERAL_TYPE next() {
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

            Node<GENERAL_TYPE> lastNext = lastReturned.next;
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
        public GENERAL_TYPE prev() {
            checkModification();
            lastReturned = prev;
            prev = prev.prev;
            prevIndex--;
            return lastReturned.item;
        }
    }

    private static class Node<NODE_TYPE> {
        NODE_TYPE item;
        Node<NODE_TYPE> next;
        Node<NODE_TYPE> prev;

        Node(Node<NODE_TYPE> prev, NODE_TYPE element, Node<NODE_TYPE> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public void map(ITypeConverter<T1 extends Comparable, Comparable> converter) {
        DoubleLinkedList<?> resultDoubleLinkedList = new DoubleLinkedList<>();
        for (GENERAL_TYPE element : this) {
            resultDoubleLinkedList.add(converter.aply(element));
        }
    }

}
