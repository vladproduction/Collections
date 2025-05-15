package com.vladproduction.app02.list.linkedlist;

import java.util.LinkedList;
import java.util.Queue;

public class LinkedListsQueue {
    public static void main(String[] args) {

        //create a queue as LinkedList:
        Queue<Person> queue = new LinkedList<>();
        queue.add(new Person("Alex", 21));
        queue.add(new Person("Anna", 35));
        queue.add(new Person("Fred", 19));
        queue.add(new Person("John", 30));
        queue.add(new Person("Bob", 23));

        queues(queue);

    }

    private static void queues(Queue<Person> queue) {

        System.out.println("queue - start positions: ");
        System.out.println(queue);
        System.out.println("who is on top: " + queue.peek());//just to pick (no remove)

        System.out.println("pool() method: " + queue.poll()); //return value and remove from queue
        System.out.println("queue size: " + queue.size()); //check size, which is 4

        System.out.println("who is on top now: " + queue.peek()); //check who is on top now: Anna

        System.out.println("queue - current positions: ");
        System.out.println(queue);

    }

}
