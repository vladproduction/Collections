package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.delayqueue.classic_problems;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Problem 2: Session Timeout Manager
 * Scenario: Invalidate sessions after timeout using delay logic.
 * */
public class SessionManager {
    public static void main(String[] args) throws InterruptedException {

        DelayQueue<Session> sessionQueue = new DelayQueue<>();

        sessionQueue.put(new Session("session-A", 3000));
        sessionQueue.put(new Session("session-B", 1000));

        while (!sessionQueue.isEmpty()) {
            Session expired = sessionQueue.take();
            System.out.println("Session expired: " + expired.getSessionId());
        }

    }


    static class Session implements Delayed {
        private final String sessionId;
        private final long expireAt;

        public Session(String sessionId, long expireAt) {
            this.sessionId = sessionId;
            this.expireAt = System.currentTimeMillis() + expireAt;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(expireAt - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.expireAt, ((Session) o).expireAt);
        }

        public String getSessionId() {
            return sessionId;
        }
    }
}
