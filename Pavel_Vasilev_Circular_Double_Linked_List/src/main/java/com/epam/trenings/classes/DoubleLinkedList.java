package com.epam.trenings.classes;


import java.util.Iterator;
import java.util.Objects;

/**
 * Created by Pol on 5/9/2016.
 */
public class DoubleLinkedList<T extends Comparable<T>> implements Iterable<T>, IReversIterable<T> {
    private int size = 0;
    private Node<T> first;
    private Node<T> last;

    public T get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    public T set(int index, T element) {
        checkElementIndex(index);
        Node<T> x = node(index);
        T oldVal = x.item;
        x.item = element;
        return oldVal;
    }

    public boolean add(T t) {
        final Node<T> locLast = last;
        final Node<T> newNode = new Node<>(locLast, t, null);
        last = newNode;
        if (locLast == null) {
            first = newNode;
            newNode.prev = newNode;
        }
        else {
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
        checkElementIndex(index);
        return clearLink(node(index));
    }

    private T clearLink(Node<T> x) {
        // assert x != null;
        final T element = x.item;
        final Node<T> next = x.next;
        final Node<T> prev = x.prev;

        if (prev == last) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == first) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }



    void linkBefore(T t, Node<T> node) {
        final Node<T> prev = node.prev;
        final Node<T> locLast = last;
        final Node<T> newNode = new Node<>(prev, t, node);
        node.prev = newNode;
        if (prev == null) {
            first = newNode;
            newNode.prev = locLast;
        }
        else
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

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }


    public void coctailSort() {
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


        MyItr(int index) {
            next = (index == size) ? node(0) : node(index);
            prev = (index == 0) ? node(size - 1) : node(index);
            nextIndex = index;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public T next() {
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
