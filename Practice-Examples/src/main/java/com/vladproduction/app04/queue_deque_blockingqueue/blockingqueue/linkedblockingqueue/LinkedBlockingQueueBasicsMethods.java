package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.linkedblockingqueue;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueBasicsMethods {
    public static void main(String[] args) throws InterruptedException {

        /*LinkedBlockingQueue is an optionally bounded, thread-safe blocking queue that uses a linked node structure internally.
          FIFO ordering.
          Can be bounded (if capacity is passed to constructor) or unbounded (Integer.MAX_VALUE by default).
          Useful for producer-consumer scenarios with heavy contention.*/

        // Unbounded queue (Integer.MAX_VALUE capacity by default)
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

        // Bounded queue
        LinkedBlockingQueue<String> boundedQueue = new LinkedBlockingQueue<>(3);

        // Add elements
        queue.put("A");       // blocks if full
        queue.offer("B");     // returns false if full
        queue.add("C");       // throws IllegalStateException if full

        // Remove elements
        queue.take();         // blocks if empty
        queue.poll();         // returns null if empty
        queue.remove();       // throws NoSuchElementException if empty

        // Peek
        queue.peek();         // returns head or null
        queue.element();      // returns head or throws exception

        //Iterators are weakly consistent (no ConcurrentModificationException).
        for (String item : queue) {
            System.out.println(item);
        }

        //removing elements
        queue.remove("B");    // Remove specific element
        queue.clear();        // Remove all elements

    }
}
