package com.vladproduction.ch01_bag.typesafety;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BagResizingImpl<T> implements Bag<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private T[] data;
    private int size;
    private int modCount; // For fail-fast iterator behavior
    
    @SuppressWarnings("unchecked")
    public BagResizingImpl(int capacity) {
        if(capacity < 0){
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        data = (T[]) new Object[capacity];
        size = 0;
        modCount = 0;
    }

    public BagResizingImpl() {
        this(DEFAULT_CAPACITY); //default capacity
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
        if(item == null) {
            throw new IllegalArgumentException("Cannot add null item to the bag");
        }
        ensureCapacity();
        data[size++] = item;
        modCount++;
    }

    private void ensureCapacity(){
        if(size >= data.length){
            int newCapacity = Math.max(1, data.length * 2);
            resize(newCapacity);
        }
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        T[] newData = (T[])new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    @Override
    public void remove(T item) {
        if(item == null){
            throw new IllegalArgumentException("Cannot remove null item from the bag");
        }
        for (int i = 0; i < size; i++) {
            if(data[i].equals(item)){
                //shift elements to the left to fill the gap
                System.arraycopy(data, i + 1, data, i, size - i - 1);
                data[--size] = null;                            //GC will collect this and we are decrementing size correctly
                modCount++;
                return;                                         //remove only the first occurrence
            }
        }
        // if item not found - we can throw an exception or just return silently
        // here we'll return silently (similar to ArrayList behavior)
    }

    @Override
    public Iterator<T> iterator() {
        return new BagIterator();
    }

    @Override
    public String toString() {
        // we only show actual elements, not the entire internal array
        return Arrays.toString(Arrays.copyOf(data, size));
    }

    //some additional methods
    @Override
    public int capacity(){
        return data.length;
    }

    @Override
    public void clear(){
        for (int i = 0; i < size; i++) {
            data[i] = null;                              //GC will collect this
        }
        size = 0;
        modCount++;
    }

    @Override
    public boolean contains(T item){
        if(item == null) return false;
        for (int i = 0; i < size; i++) {
            if(data[i].equals(item)){
                return true;
            }
        }
        return false;
    }


    // Custom iterator with proper remove functionality and fail-fast behavior
    private class BagIterator implements Iterator<T>{

        private int current = 0;
        private int lastReturned = -1;
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return current < size;        //false if the next element does not exist
        }

        @Override
        public T next() {
            checkForModification();
            if(!hasNext()){
                throw new NoSuchElementException("No more elements in the bag");
            }
            lastReturned = current;
            return data[current++];
        }

        @Override
        public void remove() {
            checkForModification();
            if(lastReturned < 0){
                throw new IllegalStateException("next() must be called before remove()");
            }
            try{
                //remove the element at the last returned position
                System.arraycopy(data, lastReturned + 1, data, lastReturned, size - lastReturned - 1);
                data[--size] = null;
                current = lastReturned;                     //adjust current position
                lastReturned = -1;
                expectedModCount = ++modCount;
            }catch (IndexOutOfBoundsException e){
                throw new IllegalStateException("Iterator remove failed", e);
            }
        }

        private void checkForModification(){
            if(modCount != expectedModCount){
                throw new ConcurrentModificationException("Bag was modified during modification");
            }
        }
    }

}











