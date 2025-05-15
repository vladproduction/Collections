package com.vladproduction.app03.set.treeset;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetHowToRemoveElements {
    public static void main(String[] args) {

        TreeSet<String> countries = new TreeSet<>();
        countries.add("Germany");
        countries.add("France");
        countries.add("Spain");
        countries.add("Italy");
        countries.add("Finland");

        System.out.println("Initial TreeSet (sorted): " + countries);

        // 1. Remove a specific element
        countries.remove("Spain");
        System.out.println("\n1. After remove(\"Spain\"): " + countries);

        // 2. Remove first and last elements (specific to TreeSet)
        countries.pollFirst();  // removes the lowest element
        countries.pollLast();   // removes the highest element
        System.out.println("\n2. After pollFirst() and pollLast(): " + countries);

        // Re-populate for next examples
        countries.clear();
        countries.add("Norway");
        countries.add("Sweden");
        countries.add("Denmark");
        countries.add("Finland");
        countries.add("Iceland");

        // 3. Safe removal while iterating using iterator
        Iterator<String> iterator = countries.iterator();
        while (iterator.hasNext()) {
            String country = iterator.next();
            if (country.startsWith("S")) {
                iterator.remove(); // safe removal
            }
        }
        System.out.println("\n3. After safely removing elements starting with 'S': " + countries);

        // 4. Remove using removeIf (Java 8+)
        countries.removeIf(country -> country.endsWith("land"));
        System.out.println("\n4. After removeIf(endsWith 'land'): " + countries);

        // 5. Clear the entire set
        countries.clear();
        System.out.println("\n5. After clear(): " + countries);
        System.out.println("Is empty? " + countries.isEmpty());

    }
}
