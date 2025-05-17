package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.delayqueue.classic_problems;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Problem 1: Retry Failed Tasks with Backoff Delay
 * Scenario: You want to retry failed tasks after a delay.
 * */
public class RetryTaskScheduler {
    public static void main(String[] args) {

        DelayQueue<RetryTask> queue = new DelayQueue<>();

        queue.put(new RetryTask("Task-1", 4000L));
        queue.put(new RetryTask("Task-2", 2000L));
        queue.put(new RetryTask("Task-3", 7000L));

        while (!queue.isEmpty()){
            try{
                RetryTask taken = queue.take();
                System.out.println("Retrying: " + taken.taskName());
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\nFinished processing all tasks.");
    }

    record RetryTask(String taskName, long retryTime) implements Delayed{

        RetryTask(String taskName, long retryTime) {
            this.taskName = taskName;
            this.retryTime = System.currentTimeMillis() + retryTime;
        }

        @Override
        public String taskName() {
            return taskName;
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long delay = retryTime - System.currentTimeMillis();
            return unit.convert(delay, TimeUnit.MILLISECONDS);
        }
    }
}
