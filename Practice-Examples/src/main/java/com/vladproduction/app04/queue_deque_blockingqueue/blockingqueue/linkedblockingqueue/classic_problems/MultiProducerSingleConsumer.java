package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.linkedblockingqueue.classic_problems;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Problem 1: Producer-Consumer with Many Producers
 * Scenario: Multiple producers and a single consumer working with a shared queue.
 * */
public class MultiProducerSingleConsumer {

    public static final int BUFFER_SIZE = 10;
    private static final LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(BUFFER_SIZE);

    public static void main(String[] args) {

        //producer task
        Runnable producer = () -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    queue.put(i);
                    System.out.println(Thread.currentThread().getName() + " Produced: " + i);
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        //consumer task
        Runnable consumer = () -> {
            try{
                for (int i = 1; i <= 15; i++) {
                    int value = queue.take();
                    System.out.println(Thread.currentThread().getName() + " Consumed: " + value);
                    TimeUnit.MILLISECONDS.sleep(150);
                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        };

        new Thread(producer, "Producer-1").start();
        new Thread(producer, "Producer-2").start();
        new Thread(producer, "Producer-3").start();

        new Thread(consumer, "Consumer-1").start();

    }

}

















