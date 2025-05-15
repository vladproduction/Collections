package com.vladproduction.app02.list.stack;

import java.util.Stack;

public class MyStack {

    private final Stack<String> stack = new Stack<>();

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push("A");
        stack.push("B");
        stack.push("C");

        stack.display();
        System.out.println("Pop: " + stack.pop());
        stack.display();

        System.out.println("Size: " + stack.size());

        System.out.println("Peek: " + stack.peek());
        stack.display();

        System.out.println("Is empty? " + stack.isEmpty());
    }

    public void push(String item) {
        stack.push(item);
    }

    public String pop() {
        return stack.pop();
    }

    public String peek() {
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }

    public void display() {
        System.out.println("Stack: " + stack);
    }

}
