package com.vladproduction.ch04_stack.arraylist;

import com.vladproduction.ch04_stack.MyStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Uses ArrayList internally
 * Dynamic resizing
 * Best for: General purpose use
 * */
public class MyStackArrayListImpl<T> implements MyStack<T> {

    private final List<T> elements;

    public MyStackArrayListImpl() {
        this.elements = new ArrayList<>();
    }

    @Override
    public void push(T element) {
        if(element == null){
            throw new IllegalArgumentException("Element cannot be null");
        }
        elements.add(element);
    }

    @Override
    public T pop() {
        if(elements.isEmpty()){
            throw new IllegalStateException("Stack is empty - pop() cannot be called");
        }
        return elements.remove(elements.size() - 1);
    }

    @Override
    public T peek() {
        if(elements.isEmpty()){
            throw new IllegalStateException("Stack is empty - peek() cannot be called");
        }
        return elements.get(elements.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void clear() {
        elements.clear();
    }

    @Override
    public boolean contains(T element) {
        if(element == null){
            throw new IllegalArgumentException("Element cannot be null");
        }
        return elements.contains(element);
    }

    @Override
    public Object[] toArray() {
        return elements.toArray();
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Stack: []";
        if (size() == 1) return "Stack: [" + elements.get(0) + "] <- top of stack";
        return "Stack: " + elements + " <-- top of stack";
    }
}




















