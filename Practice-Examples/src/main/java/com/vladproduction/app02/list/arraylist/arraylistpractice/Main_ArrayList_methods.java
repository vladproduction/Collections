package com.vladproduction.app02.list.arraylist.arraylistpractice;

import java.util.ArrayList;

/**
 * Created by vladproduction on 23-Mar-24
 */

public class Main_ArrayList_methods {
    public static void main(String[] args) {

        //dynamic array
        ArrayList<Integer> integers = new ArrayList<>();

        //add:
        for (int i = 0; i < 10; i++) {
            integers.add(i);
        }
        System.out.print(integers);
        System.out.println();

        //get(index):
        System.out.println("get(0): " + integers.get(0));
        System.out.println("get(99): " + integers.get(9));

        //size():
        System.out.println("size(): " + integers.size());

        System.out.println("============how to iterate:================");
        System.out.println("//1) loop for");
        for (int i = 0; i < integers.size(); i++) {
            System.out.print(integers.get(i));
        }
        System.out.println();

        System.out.println("//2) iter - for each");
        for (Integer integer : integers) {
            System.out.print(integer);
        }
        System.out.println();

        System.out.println("//3) lambda:");
        integers.forEach(integer -> System.out.print(integer));


    }
}
