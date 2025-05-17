package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.linkedtransferqueue.classic_problems;

import java.util.concurrent.LinkedTransferQueue;

/**
 * Problem 1: Producer-Consumer with Synchronous Transfer
 * Scenario: Transfer element only when a consumer is ready.
 * */
public class SynchronousExchange {
    public static void main(String[] args) {
        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();

        Thread producer = new Thread(() -> {
            try {
                System.out.println("Producing...");
                queue.transfer("SYNCHRONIZED DATA"); // waits
                System.out.println("Produced and handed off");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("Consuming...");
                System.out.println("Received: " + queue.take());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}
