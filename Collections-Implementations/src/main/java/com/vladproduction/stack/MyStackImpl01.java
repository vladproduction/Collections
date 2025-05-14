package com.vladproduction.stack;

public class MyStackImpl01 implements MyStack{
    private Object [] data;
    private int capacity;
    private int size;

    public MyStackImpl01() {
        this(10);
    }

    public MyStackImpl01(int capacity) {
        this.capacity = capacity;
        data = new Object[capacity];
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void push(Object o) {
        if (size>=data.length){
            Object [] tmp = new Object[data.length+5];
            System.arraycopy(data,0,tmp,0,data.length);
            data=tmp;
        }
        data[size] = o;
        size++;
    }

    @Override
    public Object peek() {
        return data [size-1]; //top element of []
    }

    @Override
    public Object pop() {
        Object topItem = data[size-1];
        data [size-1] = null;
        size--;
        return topItem;
    }
}
