package com.vladproduction.app03.set.treeset;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetHowToIterate {
    public static void main(String[] args) {

        // Create a TreeSet and add some elements
        TreeSet<String> fruits = new TreeSet<>();
        fruits.add("Banana");
        fruits.add("Apple");
        fruits.add("Cherry");
        fruits.add("Date");

        System.out.println("TreeSet contents (sorted): " + fruits);

        // 1. Enhanced for-loop (for-each)
        System.out.println("\n1. Iteration using enhanced for-loop:");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        // 2. Using Iterator
        System.out.println("\n2. Iteration using Iterator:");
        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // 3. Using Java 8 forEach with lambda
        System.out.println("\n3. Iteration using forEach with lambda:");
        fruits.forEach(fruit -> System.out.println(fruit));

        // 4. Using forEach with method reference
        System.out.println("\n4. Iteration using method reference:");
        fruits.forEach(System.out::println);

        // 5. Using stream()
        System.out.println("\n5. Iteration using Stream API:");
        fruits.stream().forEach(System.out::println);

        // 6. Using descendingIterator() (reverse order)
        System.out.println("\n6. Iteration in reverse order using descendingIterator:");
        Iterator<String> descendingIterator = fruits.descendingIterator();
        while (descendingIterator.hasNext()) {
            System.out.println(descendingIterator.next());
        }
    }

}
