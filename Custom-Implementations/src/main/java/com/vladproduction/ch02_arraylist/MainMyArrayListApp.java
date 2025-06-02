package com.vladproduction.ch02_arraylist;

import java.util.Iterator;

public class MainMyArrayListApp {
    public static void main(String[] args) {

        MyArrayList<Integer> list = new MyArrayListImpl<>();

        System.out.println("\n===DEFAULT STATE===");
        System.out.println("toString: " + list);
        System.out.println("size: " + list.size());
        System.out.println("isEmpty: " + list.isEmpty());
        System.out.println("capacity: " + list.capacity());

        System.out.println("\n===ADD 5 ELEMENTS===");
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println("toString: " + list);

        System.out.println("\n===AFTER ADD 10 ELEMENTS TOTALLY===");
        for (int i = 6; i < 11; i++) {
            list.add(i);
        }
        System.out.println("toString: " + list);
        System.out.println("capacity: " + list.capacity());

        System.out.println("\n===AFTER ADD 11-th ELEMENT CAPACITY MUST INCREASE DOUBLY===");
        list.add(11);
        System.out.println("toString: " + list);
        System.out.println("capacity: " + list.capacity());

        System.out.println("\n===PARAMETERIZED CONSTRUCTOR===");
        System.out.println("init capacity is 3");
        list = new MyArrayListImpl<>(3);
        list.add(1);
        list.add(2);
        System.out.println("toString: " + list);
        System.out.println("capacity: " + list.capacity());

        System.out.println("\n===CHECK HOW INCREASE CAPACITY FROM INITIALIZED VALUE 3 --> 6 ===");
        list.add(3);
        list.add(4);
        System.out.println("toString: " + list);
        System.out.println("capacity: " + list.capacity());

        System.out.println("\n===CHECK CLEAR METHOD ===");
        list.clear();
        System.out.println("toString: " + list);
        System.out.println("size after clear: " + list.size());
        System.out.println("capacity after clear: " + list.capacity());

        System.out.println("\n===CHECK CONTAIN METHOD ===");
        list = new MyArrayListImpl<>();
        list.add(1000);
        list.add(2000);
        list.add(3000);

        System.out.println("contains 2000: " + list.contains(2000)); // true
        System.out.println("contains 500: " + list.contains(500));   // false

        System.out.println("\n===TEST GET BY INDEX===");
        System.out.println("Element at index 0: " + list.getByIndex(0)); // 1000
        System.out.println("Element at index 1: " + list.getByIndex(1)); // 2000
        System.out.println("Element at index 2: " + list.getByIndex(2)); // 3000

        System.out.println("\n===TEST SET BY INDEX===");
        System.out.println("Before set: " + list);
        Integer oldValue = list.setByIndex(1, 9999);
        System.out.println("Old value at index 1: " + oldValue); // 2000
        System.out.println("After set: " + list);

        System.out.println("\n===TEST REMOVE BY INDEX===");
        System.out.println("Before remove: " + list);
        list.removeByIndex(1); // Remove 9999
        System.out.println("After remove index 1: " + list);
        System.out.println("Size: " + list.size());

        System.out.println("\n===TEST ITERATOR===");
        list.add(4000);
        list.add(5000);
        System.out.println("Current list: " + list);

        System.out.print("Using iterator: ");
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        System.out.println("\n===ENHANCED FOR LOOP (using iterator)===");
        System.out.print("Enhanced for loop: ");
        for (Integer value : list) {
            System.out.print(value + " ");
        }
        System.out.println();

        System.out.println("\n===TEST ITERATOR REMOVE===");
        System.out.println("Before iterator remove: " + list);
        iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer value = iterator.next();
            if (value.equals(3000)) {
                iterator.remove(); // Remove 3000
                break;
            }
        }
        System.out.println("After iterator remove: " + list);

        System.out.println("\n===ERROR HANDLING TESTS===");
        try {
            list.getByIndex(100); // Should throw IndexOutOfBoundsException
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        try {
            list.add(null); // Should throw IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        System.out.println("\n===FINAL STATE===");
        System.out.println("Final list: " + list);
        System.out.println("Final size: " + list.size());
        System.out.println("Final capacity: " + list.capacity());


    }
}
