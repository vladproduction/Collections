package com.vladproduction.app02.list.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class LinkedList02 {
    public static void main(String[] args) {
        //---ArrayList---
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(1);
        list1.add(1);
        System.out.println(list1); //[1, 1, 1]

        //---LinkedList---
        //LinkedList<String> list2 = new LinkedList<>();
        //List<String> list2 = new LinkedList<>();
        List<String> list2 = new ArrayList<>(10);
        list2.add("A");
        list2.add("B");
        list2.add("B2");
        list2.add("C");
        list2.add("D");
        list2.add("E");
        list2.add("F");
        System.out.println(list2);
        for (int i = 0; i < list2.size(); i++) {
            System.out.println(list2.get(i));
        }
        System.out.println("----------------------");
        for (String s : list2) {
            System.out.println(s);
        }
        list2.remove("B2");
        System.out.println("----------------------");
        for (String s : list2) {
            System.out.println(s);
        }
        list2.set(1,"A2");
        System.out.println("----------------------");
        for (String s : list2) {
            System.out.println(s);
        }

    }
}
