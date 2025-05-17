package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.priorityblockingqueue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueBasicMethods {
    public static void main(String[] args) throws InterruptedException {

        /*
        PriorityBlockingQueue is an unbounded, thread-safe, non-FIFO queue that orders elements based on natural ordering or a provided Comparator.
        Not bounded — size grows as needed.
        Internally backed by a heap.
        No blocking on insertion (put() == add()).
        Retrieval blocks if the queue is empty, but not when it's full (because it can't be "full").
        */

        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();

        // Add elements
        queue.add(4);
        queue.add(9);
        queue.put(2);
        queue.offer(3);

        // Remove elements
        queue.take();   // Blocks if empty
        queue.poll();   // Returns null if empty

        // Peek
        queue.peek();   // Returns smallest (priority) element

        //Custom Comparator Example
        PriorityBlockingQueue<String> queueWithComparator = new PriorityBlockingQueue<>(10, Comparator.comparingInt(String::length));
        queueWithComparator.add("apple");
        queueWithComparator.add("kiwi");
        queueWithComparator.add("banana");

        while (!queueWithComparator.isEmpty()) {
            System.out.println(queueWithComparator.poll()); // Output: kiwi, apple, banana
        }

        //Iteration: not guaranteed in priority order
        //iteration order != priority order
        for (Integer item : queue) {
            System.out.println(item);
        }

        //Removing Elements
        queue.remove(2);     // Removes specific object
        queue.clear();       // Clears all elements


    }
}
