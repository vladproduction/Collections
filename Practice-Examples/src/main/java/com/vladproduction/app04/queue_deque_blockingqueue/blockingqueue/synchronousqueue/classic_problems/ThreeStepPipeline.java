package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.synchronousqueue.classic_problems;

import java.util.concurrent.SynchronousQueue;

/**
 * Problem 3: Pipeline Step Control
 * Goal: Ensure data flows through multiple threads in lock-step.
 * */
public class ThreeStepPipeline {
    public static void main(String[] args) {
        SynchronousQueue<String> stage1 = new SynchronousQueue<>();
        SynchronousQueue<String> stage2 = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                stage1.put("raw-data");
                System.out.println("Stage 1 done");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        new Thread(() -> {
            try {
                String data = stage1.take();
                String processed = data.toUpperCase();
                stage2.put(processed);
                System.out.println("Stage 2 done");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        new Thread(() -> {
            try {
                String finalData = stage2.take();
                System.out.println("Final Output: " + finalData);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
