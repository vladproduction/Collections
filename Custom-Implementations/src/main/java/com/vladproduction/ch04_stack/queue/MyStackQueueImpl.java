package com.vladproduction.ch04_stack.queue;

import com.vladproduction.ch04_stack.MyStack;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Uses two Queue objects (custom approach)
 * Implements stack using FIFO queues
 * Push operation moves all elements to maintain LIFO order
 * Best for: Academic/interview purposes (demonstrates algorithm knowledge)
 * */
public class MyStackQueueImpl<T> implements MyStack<T> {

    private Queue<T> mainQueue;
    private Queue<T> tempQueue;

    public MyStackQueueImpl() {
        this.mainQueue = new ArrayDeque<>();
        this.tempQueue = new ArrayDeque<>();
    }

    @Override
    public void push(T element) {
        //add a new element to tempQueue
        if(element == null){
            throw new IllegalArgumentException("Element cannot be null");
        }
        tempQueue.offer(element);

        //move all elements from mainQueue to tempQueue
        while (!mainQueue.isEmpty()){
            tempQueue.offer(mainQueue.poll());
        }

        //swap the queues
        Queue<T> temp = mainQueue;
        mainQueue = tempQueue;
        tempQueue = temp;
    }

    @Override
    public T pop() {
        if(mainQueue.isEmpty()){
            throw new IllegalStateException("Stack is empty - pop() cannot be called");
        }
        return mainQueue.poll();
    }

    @Override
    public T peek() {
        if(mainQueue.isEmpty()){
            throw new IllegalStateException("Stack is empty - peek() cannot be called");
        }
        return mainQueue.peek();
    }

    @Override
    public boolean isEmpty() {
        return mainQueue.isEmpty();
    }

    @Override
    public int size() {
        return mainQueue.size();
    }

    @Override
    public void clear() {
        mainQueue.clear();
        tempQueue.clear();
    }

    @Override
    public boolean contains(T element) {
        if(element == null){
            throw new IllegalArgumentException("Element cannot be null");
        }
        return mainQueue.contains(element);
    }

    @Override
    public Object[] toArray() {
        return mainQueue.toArray();
    }

    @Override
    public String toString() {
        if(mainQueue.isEmpty()){
            return "Stack: []";
        }
        return "Stack: " + mainQueue + " <-- top of stack";
    }
}
