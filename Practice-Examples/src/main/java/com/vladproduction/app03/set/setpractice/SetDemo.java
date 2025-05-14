package com.vladproduction.app03.set.setpractice;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {
    public static void main(String[] args) {

        //no order
        Set<String > hashSet = new HashSet<>();

        //order guaranty
        Set<String > linkedHashSet = new LinkedHashSet<>();

        //sorting by natural order
        //a<b<c
        //aa<ab
        Set<String > treeSet = new TreeSet<>();

        treeSet.add("Mike");
        treeSet.add("Yie");
        treeSet.add("Bob");
        treeSet.add("Tom");
        treeSet.add("Garry");
        treeSet.add("Mag");

        for (String name : treeSet) {
            System.out.println(name);
        }

        //---------methods examples-----------
        System.out.println("--------------methods examples---------");
        Set<Integer> numbersSet = new HashSet<>();
        numbersSet.add(10);
        numbersSet.add(-9);
        numbersSet.add(1);
        numbersSet.add(0);
        numbersSet.add(25);

        System.out.println("contains:");
        boolean contain_0 = numbersSet.contains(0);
        System.out.println("contain_0 = " + contain_0); //true
        boolean contain_negative_100 = numbersSet.contains(-100);
        System.out.println("contain_negative_100 = " + contain_negative_100); //false

        System.out.println("size:");
        System.out.println(numbersSet.size());

        //how set works with duplicates:
        System.out.println("-------------how set works with duplicates:---------------");
        numbersSet.add(0); //not added
        numbersSet.add(25); //not added

        for (Integer integer : numbersSet) {
            System.out.println(integer);
        }

    }
}
