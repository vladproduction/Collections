package com.vladproduction.ch06_queue.linkedlist;

import com.vladproduction.ch06_queue.MyQueue;

public class MainMyQueueLinkedListApp {
    public static void main(String[] args) {

        MyQueue<String> queue = new MyQueueLinkedListImpl<>();
        queue.enQueue("A");
        queue.enQueue("B");
        System.out.println(queue.peek()); // A
        System.out.println(queue);        // Queue: [A, B] <- front

    }
}
