package com.vladproduction.ch04_stack.queue;

import com.vladproduction.ch04_stack.MyStack;

public class MainMyStackQueueApp {
    public static void main(String[] args) {

        MyStack<Integer> stack = new MyStackQueueImpl<>();

        // Push elements
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("After pushing 1,2,3: " + stack);

        // Test peek and pop
        System.out.println("Peek: " + stack.peek());
        System.out.println("Pop: " + stack.pop());
        System.out.println("After pop: " + stack);
        System.out.println("Size: " + stack.size());
        System.out.println("Contains 2: " + stack.contains(2));

        stack.clear();
        System.out.println("After clear: " + stack);

    }
}
