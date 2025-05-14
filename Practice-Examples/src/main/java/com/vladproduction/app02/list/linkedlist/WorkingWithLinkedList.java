package com.vladproduction.app02.list.linkedlist;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

public class WorkingWithLinkedList {
    public static void main(String[] args) {

        LinkedList<Person> linkedList = new LinkedList<>();
        linkedList.add(new Person("John",35));
        linkedList.add(new Person("Gustav",31));
        linkedList.addFirst(new Person("Adam",24));
        linkedList.addLast(new Person("Tom",21));
        ListIterator<Person> personListIterator = linkedList.listIterator();
        System.out.println("---looping through list (first to last)----------");
        //now we can see from first to last nodes;
        while (personListIterator.hasNext()){
            System.out.println(personListIterator.next());
        }
        System.out.println("---looping through list (last to first)----------");
        //or from last to first;
        while (personListIterator.hasPrevious()){
            System.out.println(personListIterator.previous());
        }

    }

    private static void queues() {
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
