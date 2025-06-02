package com.vladproduction.ch03_linkedlist;

import java.util.Iterator;

public class MainMyLinkedListApp {

    public static void main(String[] args) {
        System.out.println("=== MyLinkedList Implementation Demo ===\n");

        // Create a new linked list
        MyLinkedList<String> list = new MyLinkedListImpl<>();

        // 1. Basic Operations Demo
        basicOperationsDemo(list);

        // 2. Iteration Demo
        iterationDemo(list);

        // 3. Iterator Remove Demo
        iteratorRemoveDemo();

        // 4. Numeric Operations Demo
        numericOperationsDemo();

        // 5. Edge Cases Demo
        edgeCasesDemo();

        // 6. Performance Demo
        performanceDemo();

        // 7. Concurrent Modification Demo
        concurrentModificationDemo();
    }

    private static void basicOperationsDemo(MyLinkedList<String> list) {
        System.out.println("1. === BASIC OPERATIONS DEMO ===");

        // Adding elements
        System.out.println("Adding elements: Java, Python, JavaScript, C++");
        list.add("Java");
        list.add("Python");
        list.add("JavaScript");
        list.add("C++");

        System.out.println("List: " + list);
        System.out.println("Size: " + list.size());
        System.out.println("Is empty: " + list.isEmpty());

        // Getting elements
        System.out.println("\nGetting elements by index:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Index " + i + ": " + list.getByIndex(i));
        }

        // Setting elements
        System.out.println("\nSetting index 1 to 'Kotlin':");
        String oldValue = list.setByIndex(1, "Kotlin");
        System.out.println("Old value: " + oldValue);
        System.out.println("List: " + list);

        // Contains check
        System.out.println("\nContains 'Java': " + list.contains("Java"));
        System.out.println("Contains 'Go': " + list.contains("Go"));

        // Remove by index
        System.out.println("\nRemoving element at index 2:");
        list.removeByIndex(2);
        System.out.println("List: " + list);
        System.out.println("Size: " + list.size());

        System.out.println();
    }

    private static void iterationDemo(MyLinkedList<String> list) {
        System.out.println("2. === ITERATION DEMO ===");

        System.out.println("Enhanced for-loop iteration:");
        for (String language : list) {
            System.out.println("- " + language);
        }

        System.out.println("\nTraditional iterator:");
        Iterator<String> iter = list.iterator();
        while (iter.hasNext()) {
            String language = iter.next();
            System.out.println("- " + language);
        }

        System.out.println();
    }

    private static void iteratorRemoveDemo() {
        System.out.println("3. === ITERATOR REMOVE DEMO ===");

        MyLinkedList<Integer> numbers = new MyLinkedListImpl<>();

        // Add numbers 1-10
        for (int i = 1; i <= 10; i++) {
            numbers.add(i);
        }

        System.out.println("Original list: " + numbers);

        // Remove even numbers using iterator
        Iterator<Integer> iter = numbers.iterator();
        while (iter.hasNext()) {
            Integer num = iter.next();
            if (num % 2 == 0) {
                iter.remove();
                System.out.println("Removed: " + num);
            }
        }

        System.out.println("After removing even numbers: " + numbers);
        System.out.println();
    }

    private static void numericOperationsDemo() {
        System.out.println("4. === NUMERIC OPERATIONS DEMO ===");

        MyLinkedList<Double> scores = new MyLinkedListImpl<>();
        scores.add(85.5);
        scores.add(92.0);
        scores.add(78.3);
        scores.add(96.7);
        scores.add(88.1);

        System.out.println("Test scores: " + scores);

        // Calculate average
        double sum = 0;
        for (Double score : scores) {
            sum += score;
        }
        double average = sum / scores.size();
        System.out.println("Average score: " + String.format("%.2f", average));

        // Find max score
        double max = Double.MIN_VALUE;
        for (Double score : scores) {
            if (score > max) {
                max = score;
            }
        }
        System.out.println("Highest score: " + max);

        System.out.println();
    }

    private static void edgeCasesDemo() {
        System.out.println("5. === EDGE CASES DEMO ===");

        MyLinkedList<String> edgeList = new MyLinkedListImpl<>();

        // Empty list operations
        System.out.println("Empty list: " + edgeList);
        System.out.println("Is empty: " + edgeList.isEmpty());
        System.out.println("Size: " + edgeList.size());

        // Single element operations
        edgeList.add("OnlyElement");
        System.out.println("\nSingle element list: " + edgeList);
        System.out.println("Get index 0: " + edgeList.getByIndex(0));

        // Remove the only element
        edgeList.removeByIndex(0);
        System.out.println("After removing only element: " + edgeList);
        System.out.println("Is empty: " + edgeList.isEmpty());

        // Error handling demo (commented out to avoid program termination)
        System.out.println("\nError handling examples:");
        System.out.println("(These would throw exceptions in real usage)");

        try {
            edgeList.add(null);
        } catch (IllegalArgumentException e) {
            System.out.println("- Adding null: " + e.getMessage());
        }

        try {
            edgeList.getByIndex(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("- Getting from empty list: " + e.getMessage());
        }

        System.out.println();
    }

    private static void performanceDemo() {
        System.out.println("6. === PERFORMANCE DEMO ===");

        MyLinkedList<Integer> perfList = new MyLinkedListImpl<>();

        // Time adding elements
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            perfList.add(i);
        }
        long endTime = System.nanoTime();

        System.out.println("Added 10,000 elements");
        System.out.println("Time taken: " + (endTime - startTime) / 1_000_000 + " ms");
        System.out.println("Final size: " + perfList.size());

        // Time accessing elements
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            perfList.getByIndex(i);
        }
        endTime = System.nanoTime();

        System.out.println("\nAccessed first 1,000 elements");
        System.out.println("Time taken: " + (endTime - startTime) / 1_000_000 + " ms");

        // Clear the list
        startTime = System.nanoTime();
        perfList.clear();
        endTime = System.nanoTime();

        System.out.println("\nCleared all elements");
        System.out.println("Time taken: " + (endTime - startTime) / 1_000_000 + " ms");
        System.out.println("Size after clear: " + perfList.size());

        System.out.println();
    }

    private static void concurrentModificationDemo() {
        System.out.println("7. === CONCURRENT MODIFICATION DEMO ===");

        MyLinkedList<String> concList = new MyLinkedListImpl<>();
        concList.add("A");
        concList.add("B");
        concList.add("C");
        concList.add("D");

        System.out.println("Original list: " + concList);

        // This will throw ConcurrentModificationException
        System.out.println("\nTrying to modify list during iteration:");
        System.out.println("(This would throw ConcurrentModificationException)");

        try {
            for (String item : concList) {
                System.out.println("Processing: " + item);
                if (item.equals("B")) {
                    // This will cause ConcurrentModificationException
                    concList.add("E");
                }
            }
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getClass().getSimpleName());
            System.out.println("Message: " + e.getMessage());
        }

        // Safe way to modify during iteration
        System.out.println("\nSafe way using iterator.remove():");
        Iterator<String> safeIter = concList.iterator();
        while (safeIter.hasNext()) {
            String item = safeIter.next();
            System.out.println("Processing: " + item);
            if (item.equals("C")) {
                safeIter.remove();
                System.out.println("Safely removed: " + item);
            }
        }

        System.out.println("Final list: " + concList);

        System.out.println("\n=== Demo Complete ===");
    }

}
