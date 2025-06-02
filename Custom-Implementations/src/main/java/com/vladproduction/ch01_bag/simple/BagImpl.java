package com.vladproduction.ch01_bag.simple;

import java.util.Arrays;

public class BagImpl implements Bag {

    private static final int DEFAULT_CAPACITY = 10;

    private Object [] data;
    private int size;

    public BagImpl() {
         this(DEFAULT_CAPACITY);
    }

    public BagImpl(int capacity) {
        if(capacity < 0){
            throw new IllegalArgumentException("Capacity cannot be negative.");
        }
        this.data = new Object[capacity];
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
    public void add(Object item){
        ensureCapacity();
        data[size++] = item;
    }

    private void ensureCapacity(){
        if(size >= data.length){
            int newCapacity = Math.max(1, data.length * 2); //to handle the edge case of 0 capacity
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    @Override
    public String toString() {
        // Only show elements up to current size, not the entire array
        return Arrays.toString(Arrays.copyOf(data, size));
    }

    //additional methods:
    public int capacity(){
        return data.length;
    }

    public void clear(){
        //help GC to null out references
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }
}
















