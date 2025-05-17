package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.linkedtransferqueue;

import java.util.concurrent.LinkedTransferQueue;

public class LinkedTransferQueueBasics {
    public static void main(String[] args) {

        /*LinkedTransferQueue<E> is a non-blocking, unbounded, linked-node-based concurrent queue.
        It extends AbstractQueue and implements TransferQueue<E>.
        Key Features:
        Supports producers waiting for consumers (transfer(E e)).
        Efficient in both high contention and low contention scenarios.
        Unbounded — grows as needed.
        Ideal for hand-off scenarios (e.g., thread pool work distribution).*/

        //Basic Methods:
        /*put(E e)	                    Inserts, waits if necessary (never blocks here — unbounded).
          offer(E e)	                Inserts immediately.
          poll()	                    Retrieves and removes head, or null if empty.
          take()	                    Retrieves and removes head, waits if necessary.
          transfer(E e)	                Inserts and waits until a consumer receives it.
          tryTransfer(E e)	            Immediately transfers if a consumer is waiting, else returns false.
          tryTransfer(E e, timeout)	    Waits for consumer up to timeout, returns true if successful.
          hasWaitingConsumer()	        Returns true if at least one consumer is waiting.
          getWaitingConsumerCount()	    Returns estimated number of waiting consumers.*/

        //iteration: (FIFO order like a regular queue)
        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
        queue.add("A");
        queue.add("B");
        queue.add("C");

        for (String item : queue) {
            System.out.println("Item: " + item);
        }

        //removing:
        queue.remove("B");   // removes specific element
        queue.poll();        // removes head
        queue.clear();       // removes all

    }
}
