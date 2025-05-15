package com.vladproduction.app02.list.linkedlist;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class LinkedListIterationTechniques {
    public static void main(String[] args) {

        LinkedList<String> list = new LinkedList<>(List.of("A", "B", "C", "D"));

        // For loop
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Index " + i + ": " + list.get(i));
        }

        // Enhanced for
        for (String item : list) {
            System.out.println("Enhanced for: " + item);
        }

        // Iterator
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println("Iterator: " + iterator.next());
        }

        // ListIterator (bi-directional)
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            System.out.println("ListIterator forward: " + listIterator.next());
        }
        while (listIterator.hasPrevious()) {
            System.out.println("ListIterator backward: " + listIterator.previous());
        }

        // ForEach method (Java 8+)
        list.forEach(item -> System.out.println("ForEach lambda: " + item));

    }
}
