package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.linkedtransferqueue.classic_problems;

import java.util.concurrent.LinkedTransferQueue;

/**
 * Problem 2: Thread Pool-like Job Dispatcher
 */
public class JobDispatcher {
    public static void main(String[] args) throws InterruptedException {

        LinkedTransferQueue<Runnable> queue = new LinkedTransferQueue<>();

        // Consumer workers
        for (int i = 1; i <= 3; i++) {
            int id = i;
            new Thread(() -> {
                while (true) {
                    try {
                        Runnable job = queue.take();
                        System.out.println("Worker-" + id + " executing job...");
                        job.run();
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }).start();
        }

        // Dispatch jobs
        for (int j = 1; j <= 5; j++) {
            int taskId = j;
            queue.transfer(() -> System.out.println("Job-" + taskId + " is running."));
        }
    }

}
