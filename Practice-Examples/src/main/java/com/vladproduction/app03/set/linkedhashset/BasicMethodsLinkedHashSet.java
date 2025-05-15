package com.vladproduction.app03.set.linkedhashset;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class BasicMethodsLinkedHashSet {
    public static void main(String[] args) {

        // Create a LinkedHashSet of Strings
        LinkedHashSet<String> fruits = new LinkedHashSet<>();

        // Add elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Banana"); // duplicate, will not be added

        // Print size and content
        System.out.println("Fruits (insertion order preserved): " + fruits);
        System.out.println("Size: " + fruits.size());

        // Check for presence
        System.out.println("Contains 'Banana'? " + fruits.contains("Banana"));
        System.out.println("Contains 'Orange'? " + fruits.contains("Orange"));

        // Remove an element
        fruits.remove("Apple");
        System.out.println("After removing 'Apple': " + fruits);

        // Iteration using for-each
        System.out.println("Iterating using for-each:");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        // Iteration using iterator (safe removal)
        System.out.println("Iterating using iterator (safe removal):");
        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            String fruit = iterator.next();
            if (fruit.startsWith("B")) {
                iterator.remove(); // safe to remove during iteration
            }
        }

        System.out.println("After removing items starting with 'B': " + fruits);

        // Clear the set
        fruits.clear();
        System.out.println("After clear, is empty? " + fruits.isEmpty());

    }
}
