package com.vladproduction.app05.map.collections_synchronizedmap.classic_problems;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * Simulate logging access time of users into a synchronized map.
 * */
public class UserAccessLog {

    private final Map<String, Long> accessLog = Collections.synchronizedMap(new HashMap<>());

    public void logAccess(String userId) {
        accessLog.put(userId, System.currentTimeMillis());
    }

    public void printAccessLog() {
        synchronized (accessLog) {
            accessLog.forEach((user, time) -> {
                System.out.println(user + " accessed at " + time);
            });
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UserAccessLog log = new UserAccessLog();

        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            log.logAccess(threadName);
        };

        Thread t1 = new Thread(task, "user-A");
        Thread t2 = new Thread(task, "user-B");

        t1.start(); t2.start();
        t1.join(); t2.join();

        log.printAccessLog();
    }
}
