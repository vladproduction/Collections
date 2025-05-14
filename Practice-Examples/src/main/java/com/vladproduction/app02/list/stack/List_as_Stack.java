package com.vladproduction.app02.list.stack;

import java.util.List;
import java.util.Stack;

public class List_as_Stack {
    public static void main(String[] args) {
        //could work as List interface with its methods
        List<Integer> stack = new Stack<>();
        stack.add(100);
        stack.add(200);
        stack.add(300);
        System.out.println(stack.get(0));
        System.out.println("-----check size()---------");
        System.out.println(stack.size());
        System.out.println(stack.remove(2));
        System.out.println("-----check size() after pop---------");
        System.out.println(stack.size());
        System.out.println(stack.get(1));
        System.out.println("-----check stack is empty---------");
        System.out.println(stack.isEmpty());
    }
}
