package com.vladproduction.app04.queue;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueDemo {
    public static void main(String[] args) {
        Person person1 = new Person(1);
        Person person2 = new Person(2);
        Person person3 = new Person(3);
        Person person4 = new Person(4);

//        Queue<Person> personQueue = new LinkedList<>();
        Queue<Person> personQueue = new ArrayBlockingQueue<>(10);
        personQueue.add(person3);
        personQueue.add(person2);
        personQueue.add(person4);
        personQueue.add(person1);

        //3 <--2 <--4 <--1
        /*for (Person person : personQueue) {
            System.out.println(person);
        }*/
        /*  Person{id=3}
            Person{id=2}
            Person{id=4}
            Person{id=1}
         */
        System.out.println(personQueue.remove());//Person{id=3}
        System.out.println(personQueue.peek());//Person{id=2}
        System.out.println(personQueue);
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
