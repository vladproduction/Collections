package com.vladproduction.app04.queue_deque_blockingqueue.deque.linkedlistdeque;

import java.util.Deque;
import java.util.LinkedList;

public class LinkedListDeque {
    public static void main(String[] args) {

        Deque<Integer> deque = new LinkedList<>();

        // Insert elements
        deque.addFirst(1);
        deque.addLast(2);
        deque.offerFirst(0);
        deque.offerLast(3);

        System.out.println("Deque: " + deque); // [0, 1, 2, 3]

        // Remove from both ends
        deque.pollFirst(); // 0
        deque.pollLast();  // 3

        // Iterate
        for (int value : deque) {
            System.out.println(value); // 1, 2
        }

        // Access without removing
        System.out.println("First: " + deque.peekFirst());
        System.out.println("Last: " + deque.peekLast());

        /*
        Operation: 	                Method:
        Add at front	            addFirst(e), offerFirst(e)
        Add at rear	                addLast(e), offerLast(e)
        Remove from front	        removeFirst(), pollFirst()
        Remove from rear	        removeLast(), pollLast()
        Peek front/rear	            peekFirst(), peekLast()
        Iterate	                    Enhanced for-loop
        */

    }
}
