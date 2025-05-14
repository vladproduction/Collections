package com.vladproduction.app02.list.stack;

import java.util.Stack;

public class WorkingWithStacks {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(100);
        stack.push(200);
        stack.push(300);
        System.out.println(stack.peek());
        System.out.println("-----check size()---------");
        System.out.println(stack.size());
        System.out.println(stack.pop());
        System.out.println("-----check size() after pop---------");
        System.out.println(stack.size());
        System.out.println(stack.peek());
        System.out.println("-----check stack is empty---------");
        System.out.println(stack.empty());
    }
}
