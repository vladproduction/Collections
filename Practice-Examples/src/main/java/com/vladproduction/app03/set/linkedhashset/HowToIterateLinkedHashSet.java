package com.vladproduction.app03.set.linkedhashset;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class HowToIterateLinkedHashSet {
    public static void main(String[] args) {

        // Create and populate the LinkedHashSet
        LinkedHashSet<String> cities = new LinkedHashSet<>();
        cities.add("London");
        cities.add("Paris");
        cities.add("Rome");
        cities.add("Berlin");

        System.out.println("=== Using for-each loop ===");
        for (String city : cities) {
            System.out.println(city);
        }

        System.out.println("\n=== Using iterator ===");
        Iterator<String> iterator = cities.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("\n=== Using Java 8 forEach + lambda ===");
        cities.forEach(city -> System.out.println(city));

        System.out.println("\n=== Using method reference ===");
        cities.forEach(System.out::println);

        System.out.println("\n=== Using toArray() and traditional for loop ===");
        Object[] cityArray = cities.toArray();
        for (int i = 0; i < cityArray.length; i++) {
            System.out.println(cityArray[i]);
        }

        System.out.println("\n=== Using Stream API ===");
        cities.stream().forEach(System.out::println);

    }
}
