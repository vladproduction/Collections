package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.linkedtransferqueue.classic_problems;

import java.util.concurrent.LinkedTransferQueue;

/**
 * Problem 3: Backpressure Coordination Between Threads
 * Scenario: Only produce data when a consumer is present (to control pressure).
 * */
public class BackPressureSimulator {
    public static void main(String[] args) throws InterruptedException {

        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    String msg = "Data-" + i;
                    System.out.println("Producing " + msg);
                    queue.transfer(msg); // blocks until consumed
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1500);
                    System.out.println("Consumed: " + queue.take());
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        producer.start();
        consumer.start();
    }
}
