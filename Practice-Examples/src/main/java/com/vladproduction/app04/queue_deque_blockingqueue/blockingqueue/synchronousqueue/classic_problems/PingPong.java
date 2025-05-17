package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.synchronousqueue.classic_problems;

import java.util.concurrent.SynchronousQueue;

/**
 * Problem 1: Ping-Pong Synchronization
 * Goal: Two threads print alternately: Ping – Pong.
 * */
public class PingPong {
    public static void main(String[] args) {

        SynchronousQueue<String> queue = new SynchronousQueue<>();

        Thread ping = new Thread(() -> {
            while (true) {
                try {
                    queue.put("ping");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        Thread pong = new Thread(() -> {
            while (true) {
                try {
                    String msg = queue.take();
                    System.out.println(msg + " – pong");
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        ping.start();
        pong.start();

    }
}
