package com.vladproduction.stack;

import java.util.LinkedList;

public class MyStackImpl02_LinkedList implements MyStack{
    private LinkedList data = new LinkedList();
    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public void push(Object o) {
        data.add(o);


    }

    @Override
    public Object peek() {
        return data.getLast();
    }

    @Override
    public Object pop() {
        return data.removeLast();
    }
}
