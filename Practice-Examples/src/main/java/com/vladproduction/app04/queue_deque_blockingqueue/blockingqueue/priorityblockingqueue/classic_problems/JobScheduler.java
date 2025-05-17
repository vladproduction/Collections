package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.priorityblockingqueue.classic_problems;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Problem 1: Job Scheduler with Priorities
 * Scenario: Higher-priority jobs (lower number) must be processed first.
 * */
public class JobScheduler {

    public static void main(String[] args) {

        //creating priority queue
        PriorityBlockingQueue<Job> queue = new PriorityBlockingQueue<>();

        //adding jobs to queue
        queue.offer(new Job(1, 5));
        queue.offer(new Job(2, 1));
        queue.offer(new Job(3, 3));
        queue.offer(new Job(4, 1));
        queue.offer(new Job(5, 0));

        while (!queue.isEmpty()){
            try {
                System.out.println("\nProcessing: " + queue.peek() + "; Completed : " + queue.take());

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    //model class
    record Job(int id, int priority) implements Comparable<Job> {

        @Override
        public int compareTo(Job other) {
            return Integer.compare(this.priority, other.priority); //Lower = Higher priority
        }

        @Override
        public String toString() {
            return String.format("Job: %d (priority: %d)", id, priority);
        }
    }

}
