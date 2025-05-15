package com.vladproduction.app02.list.stack;

import java.util.Stack;

public class StackTraversal {
    public static void main(String[] args) {

        Stack<String> stack = new Stack<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");

        //direct for each (LIFO view)
        for (String item : stack){
            System.out.println("Item: " + item); //bottom to top
        }

        // Classic LIFO iteration
        while (!stack.isEmpty()) {
            System.out.println("Pop: " + stack.pop());
        }

    }
}
