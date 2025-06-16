package com.vladproduction.collectionsframework.ch05_queues;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the core concepts and usage patterns of Java's BlockingQueue interface,
 * focusing on implementations like ArrayBlockingQueue and LinkedBlockingQueue.
 * Covers blocking behavior, timeouts, thread safety, and common pitfalls.
 */
public class BlockingQueueExamples {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueueExamples demo = new BlockingQueueExamples();

        System.out.println("BLOCKINGQUEUE EXAMPLES");
        System.out.println("======================\n");

        demo.basicUsage();
        demo.blockingOperations();
        demo.threadSafety();
        demo.exceptionHandling();
        demo.edgeCases();
        demo.performanceCharacteristics();

    }

    /**
     * Demonstrates basic put, take, offer, poll operations with ArrayBlockingQueue.
     */
    private void basicUsage() throws InterruptedException {
        System.out.println("1. BASIC USAGE");
        System.out.println("==============");

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

        queue.put("A");
        queue.put("B");
        queue.put("C");

        System.out.println("Queue after puts: " + queue);

        System.out.println("Take element: " + queue.take());
        System.out.println("Queue now: " + queue);

        System.out.println();
    }

    /**
     * Shows blocking behavior on full and empty queues with timeouts.
     */
    private void blockingOperations() throws InterruptedException {
        System.out.println("2. BLOCKING OPERATIONS");
        System.out.println("======================");

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(2);

        queue.put("X");
        queue.put("Y");
        System.out.println("Queue full: " + queue);

        System.out.println("Trying to offer 'Z' with 2 seconds timeout...");
        boolean added = queue.offer("Z", 2, TimeUnit.SECONDS);
        System.out.println("Offer result: " + added);

        System.out.println("Taking elements:");
        System.out.println(queue.take());
        System.out.println(queue.take());

        System.out.println("Queue empty now: " + queue);

        System.out.println("Trying to poll with 2 seconds timeout...");
        String polled = queue.poll(2, TimeUnit.SECONDS);
        System.out.println("Poll result: " + polled);

        System.out.println();
    }

    /**
     * Demonstrates thread-safe behavior by showing concurrent put/take operations.
     */
    private void threadSafety() throws InterruptedException {
        System.out.println("3. THREAD SAFETY");
        System.out.println("================");

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 3; i++) {
                    queue.put(i);
                    System.out.println("Produced: " + i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 3; i++) {
                    int val = queue.take();
                    System.out.println("Consumed: " + val);
                    Thread.sleep(800);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("Queue at end: " + queue);
        System.out.println();
    }

    /**
     * Shows exceptions thrown by BlockingQueue methods when capacity limits are exceeded.
     */
    private void exceptionHandling() {
        System.out.println("4. EXCEPTION HANDLING");
        System.out.println("=====================");

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(2);

        try {
            queue.add("A");
            queue.add("B");
            System.out.println("Queue after adds: " + queue);

            System.out.println("Adding third element using add() (should throw IllegalStateException)...");
            queue.add("C"); // throws IllegalStateException if full
        } catch (IllegalStateException e) {
            System.out.println("Caught expected exception: " + e);
        }

        System.out.println();
    }

    /**
     * Demonstrates edge cases such as null elements not allowed in BlockingQueues.
     */
    private void edgeCases() {
        System.out.println("5. EDGE CASES");
        System.out.println("=============");

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(2);

        try {
            System.out.println("Attempting to add null element...");
            queue.put(null); // NullPointerException
        } catch (NullPointerException e) {
            System.out.println("Caught expected exception: " + e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println();
    }

    /**
     * Discusses performance characteristics and best practices for BlockingQueues.
     */
    private void performanceCharacteristics() {
        System.out.println("6. PERFORMANCE CHARACTERISTICS");
        System.out.println("==============================");

        System.out.println("ArrayBlockingQueue is bounded and backed by an array, offering predictable performance.");
        System.out.println("LinkedBlockingQueue can be optionally bounded, backed by linked nodes, usually higher throughput.");
        System.out.println("Use ArrayBlockingQueue when fairness and bounded queues are required.");
        System.out.println("Use LinkedBlockingQueue for optionally unbounded capacity and higher concurrency.");
        System.out.println("BlockingQueues are designed for multi-threaded producer-consumer patterns.");

        System.out.println();
    }


}
