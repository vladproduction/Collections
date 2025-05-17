package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.linkedtransferqueue;

import java.util.concurrent.LinkedTransferQueue;

public class LinkedTransferQueueExample {
    public static void main(String[] args) {
        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();

        // Producer
        new Thread(() -> {
            try {
                System.out.println("Producer: Waiting for consumer...");
                queue.transfer("data-123"); // will block until consumer takes it
                System.out.println("Producer: Transfer completed.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // Simulate delay before consumer is ready
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                String data = queue.take();
                System.out.println("Consumer: Received -> " + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
