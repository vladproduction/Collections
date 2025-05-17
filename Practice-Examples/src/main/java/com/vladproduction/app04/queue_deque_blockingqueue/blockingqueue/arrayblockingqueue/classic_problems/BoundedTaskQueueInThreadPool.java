package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.arrayblockingqueue.classic_problems;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Problem 2: Bounded Task Queue in Thread Pool
 * Goal: Reject new tasks if queue is full (simulate thread pool's work queue).
 * */
public class BoundedTaskQueueInThreadPool {

    private static final int MAX_QUEUE_SIZE = 3;
    private static final ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(MAX_QUEUE_SIZE);

    public static void main(String[] args) {

        Runnable task = () -> System.out.println("Task executed: " + Thread.currentThread().getName());

        for (int i = 0; i < 5; i++) {
            submitTask(task);
        }

        while (!queue.isEmpty()) {
            queue.poll().run();
        }

    }

    private static void submitTask(Runnable task){
        if(!queue.offer(task)){
            System.out.println("Queue is full. Task rejected: " + Thread.currentThread().getName());

        }
    }

}
