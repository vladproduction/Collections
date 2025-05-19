package com.vladproduction.app06.iterable_iterator_listIterator.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListFailSafeDemo {
    public static void main(String[] args) {

        // Initialize a thread-safe, fail-safe list
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");

        // Create iterator to traverse the list
        Iterator<String> stringIterator = list.iterator();

        // Iteration over the list during which the list is modified
        while (stringIterator.hasNext()) {
            String value = stringIterator.next();
            System.out.println(value);
        }

        System.out.println("-------------------------------");

        // Using enhanced for-loop (internally uses iterator)
        for (String item : list) {
            System.out.println(item);
            if ("c".equals(item)) {
                // Remove "c" during iteration
                list.remove("c");
            }
        }

        // Show the list after modification
        System.out.println("Final list: " + list);
    }
}

