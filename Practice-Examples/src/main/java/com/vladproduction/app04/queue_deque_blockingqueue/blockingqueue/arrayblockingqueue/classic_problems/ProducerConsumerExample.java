package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.arrayblockingqueue.classic_problems;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Problem 1: Producer-Consumer Problem
 * Goal: Synchronize two threads so that producer waits when queue is full and consumer waits when empty.
 * */
public class ProducerConsumerExample {

    public static final int MAX_BUFFER_SIZE = 5;

    private static final ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(MAX_BUFFER_SIZE);

    public static void main(String[] args) {

        //create producer thread
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    queue.put(i);                           // blocks if full
                    System.out.println("Produced: " + i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        //create consumer thread
        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    int value = queue.take();                // blocks if empty
                    TimeUnit.MILLISECONDS.sleep(200); //short delay to simulate consuming work
                    System.out.println("Consumed: " + value);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        //start threads
        producer.start();
        consumer.start();


    }
}
