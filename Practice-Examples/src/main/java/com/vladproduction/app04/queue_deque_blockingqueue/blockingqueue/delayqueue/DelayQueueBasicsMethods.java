package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.delayqueue;

import java.util.Locale;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueBasicsMethods {
    public static void main(String[] args) {

        /*DelayQueue is a blocking queue of elements that implement the Delayed interface.
        An element can only be taken when its delay has expired.
        Internally uses a priority queue based on the delay.
        Very useful for task scheduling, retry mechanisms, and timeouts.
        It is unbounded.*/
        /*Basic Characteristics
        Requires elements to implement Delayed.
        No element is available until its delay has expired.
        Uses System.nanoTime() for timing precision.
        Thread-safe and non-fair (no guaranteed order among same-delay items).*/

        //here is example of basic usage:
        //we have to have class model that implement Delayed interface

        //create DelayedQueue for our DelayedTask class:
        DelayQueue<DelayedTask> delayQueue = new DelayQueue<>();

        //put some DelayedTask objects into the queue:
        delayQueue.put(new DelayedTask("Task-1", 3000L));
        delayQueue.put(new DelayedTask("Task-2", 1000L));
        delayQueue.put(new DelayedTask("Task-3", 2000L));

        //iteration by for-each loop does not respect delay ordering
        //should use take() or poll() methods to get the next element

        //Removing Elements
        //delayQueue.remove(task); // Removes specific task
        //delayQueue.clear();      // Removes all tasks

        //iterating by while loop until the queue is getting empty:
        while (!delayQueue.isEmpty()){
            try {
                // Retrieves and removes the head of this queue,
                // waiting if necessary until an element with an expired delay is available on this queue.
                System.out.println("Processed: " + delayQueue.take()); //here will be blocks until the delay expires
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\nFinished processing all tasks.");

    }

    static class DelayedTask implements Delayed{

        private final String name;
        private final Long startTime; //as delay in nanoseconds

        public DelayedTask(String name, Long delayInMills) {
            this.name = name;
            this.startTime = System.currentTimeMillis() + delayInMills;
        }

        //need to override these methods:

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = startTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return String.format(Locale.US, "%s (%d)", name, startTime);
        }

    }

}
