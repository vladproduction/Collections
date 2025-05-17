package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.synchronousqueue.classic_problems;

import java.util.concurrent.SynchronousQueue;

/**
 * Problem 2: Thread Handoff Coordination
 * */
public class HandoffBetweenThreads {
    public static void main(String[] args) {

        SynchronousQueue<String> handoffQueue = new SynchronousQueue<>();

        Runnable worker = () -> {
            try {
                String job = handoffQueue.take(); // Wait for job
                System.out.println(Thread.currentThread().getName() + " got job: " + job);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        new Thread(worker, "Worker-1").start();
        new Thread(worker, "Worker-2").start();

        try {
            handoffQueue.put("Job-A");
            handoffQueue.put("Job-B");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}
