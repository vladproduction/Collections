package com.vladproduction.app04.queue;

import java.util.LinkedList;
import java.util.Queue;

public class WorkingWithQueues {
    public static void main(String[] args) {
        Queue<Person> supermarketQueue = new LinkedList<>();
        supermarketQueue.add(new Person("Alex", 21));
        supermarketQueue.add(new Person("Anna", 35));
        supermarketQueue.add(new Person("Fred", 19));

        System.out.println("supermarketQueue - start position");
        System.out.println(supermarketQueue.peek());
        System.out.println(supermarketQueue.size());
        System.out.println("---after pool-------------");
        System.out.println(supermarketQueue.poll()); //return value and remove from queue
        System.out.println(supermarketQueue.peek()); //now Anna
        System.out.println(supermarketQueue.size()); //2
    }

    record Person(String name, int age){}
}
