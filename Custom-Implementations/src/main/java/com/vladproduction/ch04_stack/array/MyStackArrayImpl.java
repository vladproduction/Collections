package com.vladproduction.ch04_stack.array;

import com.vladproduction.ch04_stack.MyStack;

/**
 * Uses fixed-size array
 * Has capacity limit (prevents stack overflow)
 * Additional methods: getCapacity(), isFull()
 * Best for: Memory-constrained environments
 * */
public class MyStackArrayImpl<T> implements MyStack<T> {

    public static final int STACK_DEFAULT_LIMIT = 10;
    private final Object[] elements;
    private int top;
    private final int capacity;

    //parameterized constructor
    public MyStackArrayImpl(int capacity) {
        this.elements = new Object[capacity];
        this.top = -1;
        this.capacity = capacity;
    }

    //default constructor
    public MyStackArrayImpl() {
        this(STACK_DEFAULT_LIMIT);
    }

    @Override
    public void push(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }

        if (top >= capacity - 1) {
            throw new RuntimeException("Stack overflow - cannot push");
        }
        elements[++top] = element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty - cannot pop");
        }
        T element = (T) elements[top];
        elements[top--] = null; // Help GC
        return element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty - cannot peek");
        }
        return (T) elements[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public int size() {
        return top + 1;
    }

    @Override
    public void clear() {
        for (int i = 0; i <= top; i++) {
            elements[i] = null; // Help GC
        }
        top = -1;
    }

    @Override
    public boolean contains(T element) {
        for (int i = 0; i <= top; i++) {
            if (elements[i] != null && elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size()];
        System.arraycopy(elements, 0, result, 0, size());
        return result;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isFull() {
        return top >= capacity - 1;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Stack: []";
        StringBuilder sb = new StringBuilder("Stack: [");
        for (int i = 0; i <= top; i++) {
            sb.append(elements[i]);
            if (i < top) sb.append(", ");
        }
        sb.append("] <- top (").append(size()).append("/").append(capacity).append(")");
        return sb.toString();
    }

}
