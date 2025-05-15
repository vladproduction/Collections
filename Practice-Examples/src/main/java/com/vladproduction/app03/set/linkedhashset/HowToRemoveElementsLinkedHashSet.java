package com.vladproduction.app03.set.linkedhashset;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class HowToRemoveElementsLinkedHashSet {
    public static void main(String[] args) {

        LinkedHashSet<String> animals = new LinkedHashSet<>();
        animals.add("Dog");
        animals.add("Cat");
        animals.add("Bird");
        animals.add("Fish");
        animals.add("Cat"); // duplicate, ignored

        System.out.println("Initial Set: " + animals);

        // 1. Remove a specific element by value
        animals.remove("Fish");
        System.out.println("After remove(\"Fish\"): " + animals);

        // 2. Safe removal while iterating (using iterator)
        Iterator<String> iterator = animals.iterator();
        while (iterator.hasNext()) {
            String animal = iterator.next();
            if (animal.equals("Bird")) {
                iterator.remove(); // safe
            }
        }
        System.out.println("After safely removing 'Bird' using iterator: " + animals);

        // 3. Remove using removeIf (Java 8+)
        animals.removeIf(animal -> animal.startsWith("D"));
        System.out.println("After removeIf (startsWith 'D'): " + animals);

        // 4. Clear the entire set
        animals.clear();
        System.out.println("After clear(): " + animals);
        System.out.println("Is empty? " + animals.isEmpty());

    }
}
