package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.linkedblockingqueue.classic_problems;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Problem 3: Logging Framework Buffer
 * Scenario: Use a LinkedBlockingQueue to offload log processing to a background thread.
 * */
public class BufferedLogger {

    private static final LinkedBlockingQueue<String> logQueue = new LinkedBlockingQueue<>();
    private static volatile boolean isRunning = true;
    private static Thread loggerThread;



    public static void main(String[] args) throws InterruptedException {

        startLoggerThread(); //uncomment in case we are using method instead of static block
        log("App started");
        log("Initializing...");
        log("App running...");

        shutdownProcess();

        // Wait for logger thread to finish:
        //adding thread join ensures that logs are flushed before the application exits
        loggerThread.join(); //uncomment in case we are using method instead of static block
        System.out.println("Logging completed, program will exit.");

    }
    private static void startLoggerThread()                               //can make as method
    //static
    {                                                                //or as a static block
        loggerThread = new Thread(() -> {
            while (isRunning || !logQueue.isEmpty()) {
                try {
                    String log = logQueue.take();
                    System.out.println("LOG: " + log);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        //Use a Daemon Thread or Not:
        //If you want to ensure logs are processed, avoid setting the logger thread as a daemon. Otherwise, JVM might exit before logs are flushed.
        // Don't set as daemon if you want to ensure logs are processed
        //logger.setDaemon(true);
        loggerThread.start();
    }

    public static void log(String msg) {
        try {
            logQueue.put(msg);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void shutdownProcess() {
        isRunning = false;
    }

}
