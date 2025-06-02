package com.vladproduction.ch02_arraylist;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;

public class MyArrayListImpl<T> implements MyArrayList<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private int size;
    private T[] data;
    private int modCount; // For fail-fast iterator behavior

    //default constructor
    public MyArrayListImpl() {
        this(DEFAULT_CAPACITY);
    }

    //parameterized constructor
    @SuppressWarnings("unchecked")
    public MyArrayListImpl(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        data = (T[]) new Object[capacity];
        size = 0;
        modCount = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null item to the list");
        }
        ensureCapacity();
        data[size++] = item;
        modCount++;
    }

    @Override
    public T getByIndex(int index) {
        checkIndex(index);
        return data[index];
    }

    @Override
    public T setByIndex(int index, T newItem) {
        if (newItem == null) {
            throw new IllegalArgumentException("Cannot set null item to the list");
        }
        checkIndex(index);
        T oldValue = data[index];
        data[index] = newItem;
        modCount++;
        return oldValue;
    }

    @Override
    public void removeByIndex(int index) {
        checkIndex(index);

        //shifting elements to the left
        int moveCount = size - index - 1;
        if (moveCount > 0) {
            System.arraycopy(data, index + 1, data, index, moveCount);
        }

        //clear the last element and decreaser size
        data[--size] = null;
        modCount++;
    }

    @Override
    public boolean contains(T item) {
        if (item == null) return false;
        for (int i = 0; i < size; i++) {
            if (data[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;                              //GC will collect this
        }
        size = 0;
        modCount++;
    }

    @Override
    public int capacity() {
        return data.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MyArrayListImpl<?> that = (MyArrayListImpl<?>) o;
        if (size != that.size) return false;
        for (int i = 0; i < size; i++) {
            if (!Objects.equals(data[i], that.data[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        for (int i = 0; i < size; i++) {
            result = 31 * result + Objects.hashCode(data[i]);
        }
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(data, size));
    }

    /**
     * helpers methods:
     */
    private void ensureCapacity() {
        if (size >= data.length) {
            int newCapacity = Math.max(1, data.length * 2);
            resize(newCapacity);
        }
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        T[] newData = (T[]) new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * My custom iterator implementation class
     * - iteration;
     * - proper removing;
     * - check for concurrent modification;
     */
    private class MyArrayListIterator implements Iterator<T> {

        private int cursor = 0;
        private int lastReturned = -1;
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public T next() {
            checkForModification();
            if (cursor >= size) {
                throw new IllegalStateException("No more elements in the list");
            }
            lastReturned = cursor;
            return data[cursor++];
        }

        @Override
        public void remove() {
            if (lastReturned < 0) {
                throw new IllegalStateException("No element to remove");
            }
            checkForModification();

            try {
                MyArrayListImpl.this.removeByIndex(lastReturned);
                cursor = lastReturned;
                lastReturned = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        private void checkForModification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("List was modified during modification");
            }
        }
    }

}

















