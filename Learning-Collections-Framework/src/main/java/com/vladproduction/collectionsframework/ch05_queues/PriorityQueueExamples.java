package com.vladproduction.collectionsframework.ch05_queues;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 * Demonstrates the behavior and usage of Java's PriorityQueue.
 * Covers natural ordering, custom comparators, basic operations,
 * iteration order, exception handling, and typical edge cases.
 */
public class PriorityQueueExamples {
    public static void main(String[] args) {

        PriorityQueueExamples demo = new PriorityQueueExamples();

        System.out.println("PRIORITY QUEUE EXAMPLES");
        System.out.println("======================\n");

        demo.basicOperations();
        demo.naturalOrdering();
        demo.customComparator();
        demo.iterationOrder();
        demo.exceptionHandling();
        demo.edgeCases();


    }

    /**
     * Demonstrates basic add, peek, poll operations of PriorityQueue.
     * Shows how elements are retrieved in priority order.
     */
    private void basicOperations() {
        System.out.println("1. BASIC OPERATIONS");
        System.out.println("==================");

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(20);
        pq.offer(10);
        pq.offer(30);

        System.out.println("PriorityQueue contents (internal heap order not guaranteed): " + pq);

        System.out.println("Peek (smallest element): " + pq.peek());
        System.out.println("Poll (removes smallest): " + pq.poll());
        System.out.println("After poll: " + pq);

        System.out.println();
    }

    /**
     * Demonstrates natural ordering of elements (ascending for numbers).
     */
    private void naturalOrdering() {
        System.out.println("2. NATURAL ORDERING");
        System.out.println("===================");

        PriorityQueue<String> pq = new PriorityQueue<>();
        pq.offer("banana");
        pq.offer("apple");
        pq.offer("cherry");

        System.out.println("Elements in priority order:");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println("\n");
    }

    /**
     * Demonstrates usage of a custom Comparator to reverse natural order.
     */
    private void customComparator() {
        System.out.println("3. CUSTOM COMPARATOR");
        System.out.println("====================");

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        pq.offer(5);
        pq.offer(1);
        pq.offer(10);

        System.out.println("Elements in reverse priority order:");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println("\n");
    }

    /**
     * Shows that iteration order in PriorityQueue is unpredictable and
     * different from poll order.
     */
    private void iterationOrder() {
        System.out.println("4. ITERATION ORDER");
        System.out.println("==================");

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(3);
        pq.offer(2);
        pq.offer(4);
        pq.offer(1);

        System.out.print("Iteration order: ");
        for (Integer i : pq) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.print("Poll order: ");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println("\n");
    }

    /**
     * Demonstrates exceptions thrown by PriorityQueue methods under edge conditions.
     */
    private void exceptionHandling() {
        System.out.println("5. EXCEPTION HANDLING");
        System.out.println("=====================");

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        try {
            System.out.println("Remove from empty queue:");
            pq.remove(); // throws NoSuchElementException
        } catch (NoSuchElementException e) {
            System.out.println("Caught expected exception: " + e);
        }

        System.out.println("Poll from empty queue returns: " + pq.poll()); // returns null, no exception

        try {
            System.out.println("Element from empty queue:");
            pq.element(); // throws NoSuchElementException
        } catch (NoSuchElementException e) {
            System.out.println("Caught expected exception: " + e);
        }

        System.out.println();
    }

    /**
     * Demonstrates edge cases such as null elements and adding incompatible types.
     */
    private void edgeCases() {
        System.out.println("6. EDGE CASES");
        System.out.println("=============");

        PriorityQueue<String> pq = new PriorityQueue<>();

        try {
            System.out.println("Adding null element:");
            pq.offer(null); // Throws NullPointerException
        } catch (NullPointerException e) {
            System.out.println("Caught expected exception: " + e);
        }

        try {
            System.out.println("Adding incompatible type:");
            // unchecked cast hack to demonstrate ClassCastException at runtime
            @SuppressWarnings("unchecked")
            PriorityQueue rawQueue = pq;
            rawQueue.offer(123); // incompatible with String queue
        } catch (ClassCastException e) {
            System.out.println("Caught expected exception: " + e);
        }

        System.out.println();
    }
}
