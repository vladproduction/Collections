package com.vladproduction.bag;

public class Main01 {
    public static void main(String[] args) {
        Bag data = new BagImpl();
        System.out.println(data);
        System.out.println(data.size());
        System.out.println(data.isEmpty());

        System.out.println("add(Object o):");
        data.add("a");
        data.add("b");
        data.add("c");
        data.add("d");
        data.add("e");
        System.out.println(data);
        System.out.println(data.size());
        System.out.println(data.isEmpty());
    }
}
