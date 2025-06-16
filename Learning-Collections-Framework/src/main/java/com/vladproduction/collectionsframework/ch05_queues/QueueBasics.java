package com.vladproduction.collectionsframework.ch05_queues;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Demonstrates basic usage and characteristics of the Queue interface in Java.
 * Covers typical queue operations, null handling, iteration order, and exception behaviors.
 */
public class QueueBasics {
    public static void main(String[] args) {

        QueueBasics demo = new QueueBasics();

        System.out.println("QUEUE BASICS");
        System.out.println("============\n");

        demo.basicOperations();
        demo.nullBehavior();
        demo.iterationOrder();
        demo.exceptionsHandling();
        demo.typicalUsageScenarios();
        demo.edgeCases();

    }

    /**
     * Demonstrates basic queue operations: offer, poll, peek, and their behaviors.
     */
    private void basicOperations() {
        System.out.println("1. BASIC QUEUE OPERATIONS");
        System.out.println("=========================");

        Queue<String> queue = new LinkedList<>();

        System.out.println("Offer elements: A, B, C");
        queue.offer("A");
        queue.offer("B");
        queue.offer("C");
        System.out.println("Queue after offers: " + queue);

        System.out.println("Peek element: " + queue.peek());
        System.out.println("Poll element: " + queue.poll());
        System.out.println("Queue after poll: " + queue);

        System.out.println();
    }

    /**
     * Explains the null element behavior in Queue implementations.
     * LinkedList allows null elements, others like PriorityQueue do not.
     */
    private void nullBehavior() {
        System.out.println("2. NULL ELEMENT BEHAVIOR");
        System.out.println("========================");

        Queue<String> queue = new LinkedList<>();
        queue.offer(null);
        queue.offer("X");
        System.out.println("Queue with null element: " + queue);

        System.out.println("Peek returns: " + queue.peek());
        System.out.println("Poll returns: " + queue.poll());
        System.out.println("Queue after poll: " + queue);

        System.out.println("Note: PriorityQueue and others throw NullPointerException on null elements.\n");
    }

    /**
     * Demonstrates iteration order consistency in Queue implementations like LinkedList.
     */
    private void iterationOrder() {
        System.out.println("3. ITERATION ORDER");
        System.out.println("==================");

        Queue<String> queue = new LinkedList<>();
        queue.offer("First");
        queue.offer("Second");
        queue.offer("Third");

        System.out.print("Iterating over queue: ");
        for (String item : queue) {
            System.out.print(item + " ");
        }
        System.out.println("\nOrder matches insertion order.\n");
    }

    /**
     * Shows exception-throwing variants of queue operations and their difference from safe ones.
     */
    private void exceptionsHandling() {
        System.out.println("4. EXCEPTION HANDLING");
        System.out.println("=====================");

        Queue<String> queue = new LinkedList<>();

        try {
            System.out.println("Removing element from empty queue (remove):");
            queue.remove(); // Throws NoSuchElementException if empty
        } catch (NoSuchElementException e) {
            System.out.println("Caught expected exception: " + e);
        }

        System.out.println("Polling from empty queue returns: " + queue.poll()); // Returns null, no exception

        try {
            System.out.println("Element method on empty queue:");
            queue.element(); // Throws NoSuchElementException if empty
        } catch (NoSuchElementException e) {
            System.out.println("Caught expected exception: " + e);
        }

        System.out.println();
    }

    /**
     * Shows common queue usage patterns such as task scheduling and breadth-first traversal simulation.
     */
    private void typicalUsageScenarios() {
        System.out.println("5. TYPICAL USAGE SCENARIOS");
        System.out.println("==========================");

        // Task scheduling example
        Queue<String> tasks = new LinkedList<>();
        tasks.offer("Task1");
        tasks.offer("Task2");
        tasks.offer("Task3");

        System.out.println("Processing tasks:");
        while (!tasks.isEmpty()) {
            System.out.println("Processing " + tasks.poll());
        }

        // Simulating breadth-first traversal
        Queue<Integer> bfsQueue = new LinkedList<>();
        bfsQueue.offer(1); // root

        System.out.print("BFS simulation: ");
        while (!bfsQueue.isEmpty()) {
            int node = bfsQueue.poll();
            System.out.print(node + " ");
            // For demo, add children node*2 and node*2+1, up to 3 nodes total
            if (node < 3) {
                bfsQueue.offer(node * 2);
                bfsQueue.offer(node * 2 + 1);
            }
        }
        System.out.println("\n");
    }

    /**
     * Demonstrates edge cases like empty queue operations, duplicate elements, and large queue behavior.
     */
    private void edgeCases() {
        System.out.println("6. EDGE CASES");
        System.out.println("=============");

        Queue<String> queue = new LinkedList<>();

        System.out.println("Polling empty queue returns: " + queue.poll());
        System.out.println("Offering duplicates:");

        queue.offer("dup");
        queue.offer("dup");
        queue.offer("dup");
        System.out.println(queue);

        System.out.println("Clearing queue and adding large number of elements...");

        queue.clear();
        for (int i = 0; i < 10000; i++) {
            queue.offer("elem" + i);
        }
        System.out.println("Size after bulk insert: " + queue.size());

        System.out.println();
    }

}
