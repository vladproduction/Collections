package com.vladproduction.app02.list.linkedlist;

import java.util.LinkedList;
import java.util.List;

public class LinkedListCreation {
    public static void main(String[] args) {

        List<String> fruits = new LinkedList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Pear");

        System.out.println("Fruits: " + fruits);
    }
}
