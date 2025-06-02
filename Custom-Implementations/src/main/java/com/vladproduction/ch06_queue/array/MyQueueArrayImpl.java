package com.vladproduction.ch06_queue.array;

import com.vladproduction.ch06_queue.MyQueue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Array-based implementation of MyQueue
 * */
public class MyQueueArrayImpl<T> implements MyQueue<T> {

    public static final int QUEUE_CAPACITY = 5;

    private T[] elements;
    private int size;
    private final int capacity;

    //the default constructor with initial capacity = 5
    public MyQueueArrayImpl() {
        this(QUEUE_CAPACITY);
    }

    //parameterized constructor with custom capacity
    @SuppressWarnings( "unchecked")
    public MyQueueArrayImpl(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        this.capacity = capacity;
        this.elements = (T[]) new Object[capacity];
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enQueue(T item) {
        // Ensure there's enough capacity before adding new item
        ensureCapacity();

        // Add item at the end of the queue
        elements[size] = item;
        size++;
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (elements == null || size >= elements.length) {
            //case: if initial capacity is 0, while enQueue is called, it (capacity) will be 1
            int newCapacity = (elements == null || elements.length == 0) ? 1 : elements.length * 2;
            T[] newData = (T[]) new Object[newCapacity];
            if (elements != null) {
                System.arraycopy(elements, 0, newData, 0, size);
            }
            elements = newData;
        }
    }

    @Override
    public T deQueue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        T item = elements[0];

        for (int i = 0; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[size - 1] = null;
        size--;

        return item;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return elements[0];
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean contains(T item) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == null && item == null) {
                return true;
            }
            if (elements[i] != null && elements[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {

        return Arrays.copyOf(elements, size); // returns only the valid portion
    }

    @Override
    public Iterator<T> iterator() {
        return new MyQueueArrayIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MyQueueArrayImpl<?> that = (MyQueueArrayImpl<?>) o;
        return size == that.size && capacity == that.capacity && Objects.deepEquals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(elements), size, capacity);
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Queue: []";

        StringBuilder sb = new StringBuilder("Queue: [");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("] <- front");
        return sb.toString();
    }

    private class MyQueueArrayIterator implements Iterator<T> {

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException("No more elements in the queue");
            }
            return elements[currentIndex++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported in this iterator");
        }
    }


}
