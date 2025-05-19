package com.vladproduction.app11.generics.genericinterface;

public class Main {
    public static void main(String[] args) {

        // Integer storage
        Storage<Integer> intStorage = new GenericStorage<>();
        intStorage.add(10);
        intStorage.add(20);
        intStorage.add(30);

        System.out.println("Integer Storage:");
        intStorage.print();

        intStorage.forEach(i -> System.out.println("Doubled: " + (i * 2)));

        // String storage
        Storage<String> stringStorage = new GenericStorage<>();
        stringStorage.add("java");
        stringStorage.add("generics");
        stringStorage.add("fun");

        System.out.println("\nString Storage:");
        stringStorage.forEach(s -> System.out.println("Uppercase: " + s.toUpperCase()));

        // Double storage with sum
        Storage<Double> doubleStorage = new GenericStorage<>();
        doubleStorage.add(1.5);
        doubleStorage.add(2.25);
        doubleStorage.add(3.75);

        final double[] sum = {0.0};
        doubleStorage.forEach(d -> sum[0] += d);
        System.out.println("\nSum of doubles: " + sum[0]);
    }
}
