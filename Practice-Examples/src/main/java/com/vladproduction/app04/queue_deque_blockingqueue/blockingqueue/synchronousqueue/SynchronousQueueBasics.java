package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.synchronousqueue;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueBasics {
    public static void main(String[] args) {

        /*A SynchronousQueue<E> is a special blocking queue with no internal capacity.
        Every put() must wait for a corresponding take(), and vice versa — it’s a direct handoff.*/
        /*Key Features
        Zero capacity — no buffering.
        One-to-one handoff between threads.
        Highly efficient for producer-consumer where transfer must be immediate.
        Used internally in Java’s ThreadPoolExecutor.*/
        //Basic Methods
        /*
        put(E e)	        Blocks until another thread takes it.
        take()	            Blocks until another thread puts an element.
        offer(E e)	        Adds element only if a consumer is waiting.
        poll()	            Takes element only if a producer is waiting.
        offer(e, t, u)	    Waits up to timeout for consumer.
        poll(t, u)	        Waits up to timeout for producer.*/

        SynchronousQueue<String> queue = new SynchronousQueue<>();

        Thread producer = new Thread(() -> {
            try {
                System.out.println("Putting: Message-1");
                queue.put("Message-1");
                System.out.println("Message delivered!");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                Thread.sleep(2000); // Simulate delay
                String msg = queue.take();
                System.out.println("Consumed: " + msg);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();



    }
}
