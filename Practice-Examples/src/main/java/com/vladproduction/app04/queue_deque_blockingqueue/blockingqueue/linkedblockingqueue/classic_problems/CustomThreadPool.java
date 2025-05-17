package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.linkedblockingqueue.classic_problems;

import java.util.concurrent.*;

/**
 * Problem 2: Thread Pool Work Queue
 * Scenario: Use LinkedBlockingQueue as the backing queue for a custom thread pool.
 * */
public class CustomThreadPool {
    public static void main(String[] args) throws InterruptedException {

        //Practical fix to avoid exception:
        //Option 1: Increase queue size to prevent rejections: new LinkedBlockingQueue<>(20)
        //Option 2: Implement your own RejectedExecutionHandler: new ThreadPoolExecutor.CallerRunsPolicy():
                /*new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>(10),
                        new ThreadPoolExecutor.CallerRunsPolicy()
                );*/

        //Optionally, implement your own RejectedExecutionHandler:
        RejectedExecutionHandler handler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("Task " + r.toString() + " rejected");
                new ThreadPoolExecutor.CallerRunsPolicy();
            }
        };

        ExecutorService executor = new ThreadPoolExecutor(
                2, 4,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                //new ThreadPoolExecutor.AbortPolicy()
                handler //using your own handler
                //new ThreadPoolExecutor.CallerRunsPolicy() // default, throws exception
                /* You can replace with other policies like:
                   new ThreadPoolExecutor.CallerRunsPolicy(),
                   new ThreadPoolExecutor.DiscardPolicy(),
                   new ThreadPoolExecutor.DiscardOldestPolicy();
                */
        );

        for (int i = 1; i <= 15; i++) {
            final int taskId = i;
            executor.submit(()->{
                System.out.println("Running task: " + taskId + " on thread: " + Thread.currentThread().getName());
                try{
                    TimeUnit.MILLISECONDS.sleep(500);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            });

        }

        executor.shutdown();

        // Wait for all tasks to finish
        if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
            System.out.println("Some tasks did not finish in time");
        }

    }
}
