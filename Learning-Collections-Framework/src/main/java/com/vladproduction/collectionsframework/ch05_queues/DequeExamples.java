package com.vladproduction.collectionsframework.ch05_queues;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

/**
 * Demonstrates key features and usage patterns of Java's Deque interface
 * and its common implementation ArrayDeque. Covers stack and queue operations,
 * iteration order, exception handling, and edge cases.
 */
public class DequeExamples {
    public static void main(String[] args) {

        DequeExamples demo = new DequeExamples();

        System.out.println("DEQUE EXAMPLES");
        System.out.println("==============\n");

        demo.basicOperations();
        demo.stackBehavior();
        demo.queueBehavior();
        demo.iterationOrder();
        demo.exceptionHandling();
        demo.edgeCases();

    }

    /**
     * Demonstrates basic Deque operations: add, offer, poll, peek from both ends.
     */
    private void basicOperations() {
        System.out.println("1. BASIC OPERATIONS");
        System.out.println("===================");

        Deque<String> deque = new ArrayDeque<>();
        deque.add("A"); // addLast
        deque.addFirst("B");
        deque.offerLast("C");
        deque.offerFirst("D");

        System.out.println("Deque after additions: " + deque);

        System.out.println("Remove first: " + deque.removeFirst());
        System.out.println("Remove last: " + deque.removeLast());
        System.out.println("Peek first: " + deque.peekFirst());
        System.out.println("Peek last: " + deque.peekLast());

        System.out.println("Deque now: " + deque);
        System.out.println();
    }

    /**
     * Demonstrates Deque usage as a stack: push, pop, peek.
     */
    private void stackBehavior() {
        System.out.println("2. STACK BEHAVIOR");
        System.out.println("=================");

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("Stack after pushes: " + stack);

        System.out.println("Peek: " + stack.peek());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Stack now: " + stack);
        System.out.println();
    }

    /**
     * Demonstrates Deque usage as a queue: offer, poll, peek from the front.
     */
    private void queueBehavior() {
        System.out.println("3. QUEUE BEHAVIOR");
        System.out.println("=================");

        Deque<String> queue = new ArrayDeque<>();
        queue.offer("first");
        queue.offer("second");
        queue.offer("third");

        System.out.println("Queue after offers: " + queue);

        System.out.println("Peek front: " + queue.peek());
        System.out.println("Poll front: " + queue.poll());
        System.out.println("Queue now: " + queue);
        System.out.println();
    }

    /**
     * Shows that iteration in ArrayDeque is from front to back (FIFO order).
     */
    private void iterationOrder() {
        System.out.println("4. ITERATION ORDER");
        System.out.println("==================");

        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(10);
        deque.add(20);
        deque.add(30);

        System.out.print("Iteration order: ");
        for (Integer i : deque) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
    }

    /**
     * Demonstrates exceptions thrown by Deque methods on edge conditions.
     */
    private void exceptionHandling() {
        System.out.println("5. EXCEPTION HANDLING");
        System.out.println("=====================");

        Deque<String> deque = new ArrayDeque<>();

        try {
            System.out.println("Remove first from empty deque:");
            deque.removeFirst(); // NoSuchElementException
        } catch (NoSuchElementException e) {
            System.out.println("Caught expected exception: " + e);
        }

        try {
            System.out.println("Remove last from empty deque:");
            deque.removeLast(); // NoSuchElementException
        } catch (NoSuchElementException e) {
            System.out.println("Caught expected exception: " + e);
        }

        System.out.println("Poll first from empty deque returns: " + deque.pollFirst()); // null, no exception
        System.out.println("Poll last from empty deque returns: " + deque.pollLast()); // null, no exception

        System.out.println();
    }

    /**
     * Demonstrates edge cases like adding null elements which are not permitted.
     */
    private void edgeCases() {
        System.out.println("6. EDGE CASES");
        System.out.println("=============");

        Deque<String> deque = new ArrayDeque<>();

        try {
            System.out.println("Adding null element:");
            deque.add(null); // NullPointerException
        } catch (NullPointerException e) {
            System.out.println("Caught expected exception: " + e);
        }

        System.out.println();
    }
}
