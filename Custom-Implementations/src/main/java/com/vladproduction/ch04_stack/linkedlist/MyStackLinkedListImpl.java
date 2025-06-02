package com.vladproduction.ch04_stack.linkedlist;


import com.vladproduction.ch04_stack.MyStack;

import java.util.LinkedList;

/**
 * Uses LinkedList internally
 * Uses addLast()/removeLast() for stack operations
 * Best for: Frequent insertions/deletions
 * */
public class MyStackLinkedListImpl<T> implements MyStack<T> {

    private final LinkedList<T> elements;

    public MyStackLinkedListImpl() {
        this.elements = new LinkedList<>();
    }

    @Override
    public void push(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        elements.addLast(element); // Add to end (top of stack)
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty - cannot pop");
        }
        return elements.removeLast(); // Remove from end (top of stack)
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty - cannot peek");
        }
        return elements.peekLast(); // Peek at end (top of stack)
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
        return elements.contains(element);
    }

    @Override
    public Object[] toArray() {
        return elements.toArray();
    }

    @Override
    public String toString() {
        if (isEmpty()) return "LinkedList Stack: []";
        return "LinkedList Stack: " + elements + " <- top";
    }

}
