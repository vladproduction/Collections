package com.vladproduction.app03.set.treeset.app01;

import java.util.TreeSet;

public class MyApp01 {

    public static void main(String[] args) {
        TreeSet set = new TreeSet();
        set.add("w");
        set.add("x");
        set.add("y");
        set.add("a");
        set.add("b");
        set.add("c");
        System.out.println(set);
        set.add("b");
        System.out.println(set);
        System.out.println("---------------");
        set.clear();
        set.add(8);
        set.add(5);
        set.add(1);
        set.add(3);
        System.out.println(set);
        set.add(5);
        System.out.println(set);
        System.out.println("---------------");

    }

}

