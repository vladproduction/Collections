package com.vladproduction.ch01_bag.simple;

public class MainAppBag {
    public static void main(String[] args) {

        //create a bag
        Bag data = new BagImpl();
        System.out.println("\nCreated Bag. No elements yet");
        System.out.println(data);                   //toString()
        System.out.println(data.size());            //size()
        System.out.println(data.isEmpty());         //isEmpty()

        System.out.println("\nAdd some elements:");
        data.add("a");
        data.add("b");
        data.add("c");
        System.out.println(data);
        System.out.println(data.size());
        System.out.println(data.isEmpty());

        System.out.println("\nAdd 4-th and 5-th element: ");
        System.out.println("(as expected our bag has been increased and null cells are free for new elements)");
        data.add("d");
        data.add("e");
        System.out.println(data);
        System.out.println(data.size());
        System.out.println(data.isEmpty());
    }
}
