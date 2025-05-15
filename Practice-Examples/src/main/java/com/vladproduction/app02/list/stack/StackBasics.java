package com.vladproduction.app02.list.stack;

import java.util.Stack;

public class StackBasics {
    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();

        // Push elements (LIFO)
        stack.push(10);
        stack.push(20);
        stack.push(30);

        // Peek (top element without removing)
        System.out.println("Top of stack: " + stack.peek()); // 30

        // Pop elements
        System.out.println("Popped: " + stack.pop()); // 30
        System.out.println("After pop, top: " + stack.peek()); // 20

        // Check size and emptiness
        System.out.println("Size: " + stack.size());
        System.out.println("Is empty? " + stack.isEmpty());

    }
}
