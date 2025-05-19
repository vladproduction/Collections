package com.vladproduction.app06.iterable_iterator_listIterator.iterator;

import java.util.*;

public class ListModificationExamples {
    public static void main(String[] args) {
        ListModificationExamples demo = new ListModificationExamples();

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");

        System.out.println("Running run1 (safe modification with ListIterator):");
        demo.run1(new ArrayList<>(list));

        System.out.println("\nRunning run2 (modification during iteration causes exception):");
        try {
            demo.run2(new ArrayList<>(list));
        } catch (ConcurrentModificationException e) {
            System.out.println("Caught ConcurrentModificationException in run2");
        }

        System.out.println("\nRunning run3 (modification during iteration with iterator causes exception):");
        try {
            demo.run3(new ArrayList<>(list));
        } catch (ConcurrentModificationException e) {
            System.out.println("Caught ConcurrentModificationException in run3");
        }

        System.out.println("\nRunning run4 (for-each loop modification causes exception):");
        try {
            demo.run4(new ArrayList<>(list));
        } catch (ConcurrentModificationException e) {
            System.out.println("Caught ConcurrentModificationException in run4");
        }
    }

    // Correct: Remove element safely during iteration via ListIterator
    private void run1(List<String> list) {
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            String item = listIterator.next();
            if (item.equals("c")) {
                listIterator.remove(); // safe removal during iteration
            }
            System.out.print(item + " ");
        }
        System.out.println("\nModified list after run1: " + list);
    }

    // Will throw ConcurrentModificationException when removing from list during iteration
    private void run2(List<String> list) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if (item.equals("c")) {
                list.remove(2); // direct removal, causes exception
            }
            System.out.print(item + " ");
        }
        System.out.println("\nModified list after run2: " + list);
    }

    // Will throw ConcurrentModificationException
    private void run3(List<String> list) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            System.out.println(item);
            if ("b".equals(item)) {
                list.remove(1); // modification outside iterator
            }
        }
        System.out.println("List after run3: " + list);
    }

    // Will throw ConcurrentModificationException due to modification during for-each
    private void run4(List<String> list) {
        for (String item : list) {
            System.out.println(item);
            if ("b".equals(item)) {
                list.remove(1); // modification during for-each
            }
        }
        System.out.println("List after run4: " + list);
    }
}
