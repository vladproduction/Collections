package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.arrayblockingqueue;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueue_Add_Offer {
    public static void main(String[] args) {
        Person person1 = new Person(1);
        Person person2 = new Person(2);
        Person person3 = new Person(3);
        Person person4 = new Person(4);

        //add
        /*Queue<Person> personQueue = new ArrayBlockingQueue<>(3); //IllegalStateException: Queue full
        personQueue.add(person3);
        personQueue.add(person2);
        personQueue.add(person4);
        personQueue.add(person1);*/

        //offer
        Queue<Person> personQueue = new ArrayBlockingQueue<>(2); //IllegalStateException: Queue full
        System.out.println(personQueue.offer(person3));
        System.out.println(personQueue.offer(person2));
        System.out.println(personQueue.offer(person4));
        System.out.println(personQueue.offer(person1));

        /*  true
            true
            true
            false*/

    }

    static class Person{
        private int id;
        public Person(int id) {
            this.id = id;
        }
        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    '}';
        }
    }
}
