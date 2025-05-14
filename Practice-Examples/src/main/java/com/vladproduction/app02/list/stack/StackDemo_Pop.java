package com.vladproduction.app02.list.stack;

import java.util.Stack;

public class StackDemo_Pop {
    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        //LIFO (last in - first out)
        //5-3-1

        //push
        stack.push(5);
        stack.push(3);
        stack.push(1);

        //pop
        System.out.println("popped = " + stack.pop());
        System.out.println("popped = " + stack.pop());
        System.out.println("popped = " + stack.pop());

        /*  popped = 1
            popped = 3
            popped = 5*/
        System.out.println("popped = " + stack.pop()); //EmptyStackException


    }
}
