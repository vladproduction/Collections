package com.vladproduction.app02.list.stack;

import java.util.Stack;

public class MainStack01 {
    public static void main(String[] args) {
//        first in
//        last out

        Stack stack = new Stack();

        //add items to stack
        System.out.println("----------push()------------");
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.push("e");//the most hot blinchik
        System.out.println(stack);
        System.out.println("----------peek()------------");
        //return top; return most hot blinchik
        Object topItem = stack.peek();
        System.out.println("topItem="+topItem);
        topItem = stack.peek();
        System.out.println("topItem="+topItem);
        topItem = stack.peek();
        System.out.println("topItem="+topItem);
        System.out.println("size="+stack.size());

        System.out.println("----------pop()------------");
        Object item = stack.pop();
        System.out.println("item="+item);
        System.out.println("size="+stack.size());
        item = stack.pop();
        System.out.println("item="+item);
        System.out.println("size="+stack.size());
        item = stack.pop();
        System.out.println("item="+item);
        System.out.println("size="+stack.size());
        item = stack.pop();
        System.out.println("item="+item);
        System.out.println("size="+stack.size());
        item = stack.pop();
        System.out.println("item="+item);
        System.out.println("size="+stack.size());

        System.out.println("------pop() on empty stack---------");
        //java.util.EmptyStackException
        //stack.pop();

    }
}
