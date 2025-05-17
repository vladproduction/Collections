package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.arrayblockingqueue.classic_problems;

import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;

import static java.util.concurrent.TimeUnit.*;

/**
 * Problem 3: Logging Service with Blocking Queue
 * Goal: Asynchronously log messages using a dedicated logging thread.
 * */
public class AsyncLogger {

    private static final int MAX_QUEUE_SIZE = 10;
    private static final ArrayBlockingQueue<String> logQueue = new ArrayBlockingQueue<>(MAX_QUEUE_SIZE);
    private static volatile boolean isRunning = true;
    private static Thread loggerThread;

    public static void main(String[] args) throws InterruptedException {

        startLoggerThread();
        log("App started");
        log("Processing...");
        log("App finished");
        shutdownProcess();
        // Wait for logger thread to complete
        loggerThread.join();

    }


    private static void startLoggerThread(){

        loggerThread = new Thread(() -> {
            try {
                while (isRunning || !logQueue.isEmpty()) {
                    String message = logQueue.poll(1, SECONDS);
                    if(message != null){
                        System.out.println("LOG: " + message);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Don't set as daemon if you want to ensure logs are processed
        //loggerThread.setDaemon(true);
        loggerThread.start();

    }

    public static void log(String message){
        try{
            logQueue.put(message);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    public static void shutdownProcess(){
        isRunning = false;
    }

}
