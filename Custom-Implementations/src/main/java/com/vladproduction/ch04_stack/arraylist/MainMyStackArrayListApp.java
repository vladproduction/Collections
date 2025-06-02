package com.vladproduction.ch04_stack.arraylist;

import com.vladproduction.ch04_stack.MyStack;

public class MainMyStackArrayListApp {
    public static void main(String[] args) {

        //create a stack
        MyStack<Integer> stack = new MyStackArrayListImpl<>();

        //testing basic operations:
        System.out.println("Initial stack: " + stack);
        System.out.println("Is stack empty? " + stack.isEmpty());

        System.out.println("\n===Pushing elements onto the stack===");
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println("Stack after pushing elements: " + stack);
        System.out.println("Is stack empty? " + stack.isEmpty());
        System.out.println("Stack size: " + stack.size());

        System.out.println("\n===Popping elements from the stack===");        //deleting occurs from the top of the stack
        System.out.println("Popped element: " + stack.pop());
        System.out.println("Stack after popping: " + stack);
        System.out.println("Is stack empty? " + stack.isEmpty());
        System.out.println("Stack size: " + stack.size());

        System.out.println("\n===Peeking at the top element of the stack==="); //taken element does not get deleted
        System.out.println("Top element: " + stack.peek());
        System.out.println("Stack after peeking: " + stack);
        System.out.println("Is stack empty? " + stack.isEmpty());
        System.out.println("Stack size: " + stack.size());

        System.out.println("\n===Contains checking if an element is in the stack===");
        System.out.println("Does stack contain 3? " + stack.contains(3));
        System.out.println("Does stack contain 6? " + stack.contains(6));

        System.out.println("\n===Converting the stack to an array===");
        Object [] array = stack.toArray();
        System.out.print("Array from stack: [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if(i < array.length - 1) System.out.print(", ");
        }
        System.out.print("]");
        System.out.println();

        System.out.println("\n===Clearing the stack===");
        stack.clear();
        System.out.println("Stack after clearing: " + stack);
        System.out.println("Is stack empty? " + stack.isEmpty());
        System.out.println("Stack size: " + stack.size());

    }
}
