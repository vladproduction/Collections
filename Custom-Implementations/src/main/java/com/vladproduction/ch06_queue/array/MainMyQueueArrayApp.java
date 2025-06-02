package com.vladproduction.ch06_queue.array;

import com.vladproduction.ch06_queue.MyQueue;

public class MainMyQueueArrayApp {
    public static void main(String[] args) {

        MyQueue<Integer> queue = new MyQueueArrayImpl<>();

        System.out.println("Initial state: " + queue);
        System.out.println("Is empty? " + queue.isEmpty());

        // enQueue elements
        queue.enQueue(10);
        queue.enQueue(20);
        queue.enQueue(30);
        queue.enQueue(40);
        queue.enQueue(50);
        queue.enQueue(60); // triggers resize

        System.out.println("\nAfter enQueue 6 elements: " + queue);
        System.out.println("Size: " + queue.size());
        System.out.println("Contains 30? " + queue.contains(30));
        System.out.println("Contains 100? " + queue.contains(100));

        // peek at front
        System.out.println("\nPeek: " + queue.peek());

        // iterate over queue
        System.out.println("\nIterating over queue:");
        for (int value : queue) {
            System.out.println("Value: " + value);
        }

        // dequeue a few
        System.out.println("\nDequeued: " + queue.deQueue());
        System.out.println("Dequeued: " + queue.deQueue());
        System.out.println("Queue after 2 dequeues: " + queue);

        // convert to array
        Object[] array = queue.toArray();
        System.out.println("\nQueue toArray:");
        for (Object obj : array) {
            System.out.println(obj);
        }

        // clear the queue
        queue.clear();
        System.out.println("\nAfter clear(): " + queue);
        System.out.println("Is empty? " + queue.isEmpty());

    }
}
