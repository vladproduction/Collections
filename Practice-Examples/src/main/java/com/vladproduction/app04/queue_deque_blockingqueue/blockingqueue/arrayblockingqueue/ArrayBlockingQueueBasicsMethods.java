package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.arrayblockingqueue;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueBasicsMethods {
    public static void main(String[] args) throws InterruptedException {

        /*ArrayBlockingQueue is a bounded, FIFO (first-in-first-out) blocking queue backed by an array.
        Thread-safe (supports blocking operations for producers and consumers).
        Fixed capacity must be specified at construction.
        Blocks if full (put()), or empty (take()).*/

        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        // Add elements
        queue.put("A1");                 // Blocks if full
        queue.put("A2");
        queue.put("A3");
        queue.offer("B");               // Returns false if full
        queue.add("C");                    // Throws IllegalStateException if full

        // Remove elements
        queue.take();           // Blocks if empty
        queue.poll();           // Returns null if empty
        queue.remove();         // Throws NoSuchElementException if empty

        // Peek
        queue.peek();           // Returns head or null
        queue.element();        // Returns head or throws exception

        // Iteration is weakly consistent (reflects some elements at the time of iteration but not necessarily all concurrent updates).
        for (String item : queue) {
            System.out.println(item);
        }

        //removing elements
        queue.remove("A");       // Removes specific object
        queue.clear();              // Removes all elements



    }
}
