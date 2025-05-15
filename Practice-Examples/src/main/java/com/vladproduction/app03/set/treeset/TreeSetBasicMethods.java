package com.vladproduction.app03.set.treeset;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetBasicMethods {
    public static void main(String[] args) {

        // 1. Create a TreeSet of Integers
        TreeSet<Integer> numbers = new TreeSet<>();

        // 2. Add elements
        numbers.add(50);
        numbers.add(20);
        numbers.add(10);
        numbers.add(40);
        numbers.add(30);

        System.out.println("TreeSet (sorted): " + numbers); // auto-sorted

        // 3. Basic methods
        System.out.println("Size: " + numbers.size());
        System.out.println("Contains 30? " + numbers.contains(30));
        System.out.println("Is empty? " + numbers.isEmpty());

        // 4. Removing elements
        numbers.remove(20);
        System.out.println("After removing 20: " + numbers);

        // 5. Iterating using enhanced for loop
        System.out.println("Iterating using for-each:");
        for (int num : numbers) {
            System.out.println(num);
        }

        // 6. Iterating using iterator
        System.out.println("Iterating using iterator:");
        Iterator<Integer> it = numbers.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // 7. Navigational methods (specific to TreeSet)
        System.out.println("First element: " + numbers.first());
        System.out.println("Last element: " + numbers.last());
        System.out.println("Higher than 30: " + numbers.higher(30)); // strictly greater
        System.out.println("Lower than 30: " + numbers.lower(30));   // strictly smaller

        // 8. Using custom comparator (reverse order)
        TreeSet<String> cities = new TreeSet<>(Comparator.reverseOrder());
        cities.add("London");
        cities.add("Paris");
        cities.add("Amsterdam");
        cities.add("Berlin");

        System.out.println("Cities in reverse order: " + cities);

    }
}
