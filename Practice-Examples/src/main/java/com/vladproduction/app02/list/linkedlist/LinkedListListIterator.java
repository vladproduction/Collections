package com.vladproduction.app02.list.linkedlist;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class LinkedListListIterator {

    private static ListIterator<Person> personsListIterator;

    public static void main(String[] args) {

        //create LinkedList
        LinkedList<Person> persons = new LinkedList<>();

        //add elements
        persons.add(new Person("John", 35));
        persons.add(new Person("Gustav", 31));
        persons.addFirst(new Person("Adam", 24));
        persons.addLast(new Person("Tom", 21));

        //perform methods for iterating:
        fromFirstToLast(persons);

        System.out.println("=====");

        fromLastToFirst(persons);
    }

    private static void fromFirstToLast(List<Person> persons) {
        System.out.println("---iterating from first to last---");

        personsListIterator = persons.listIterator();

        while (personsListIterator.hasNext()) {
            System.out.println(personsListIterator.next());
        }
    }

    private static void fromLastToFirst(List<Person> persons) {
        System.out.println("---iterating from last to first---");

        personsListIterator = persons.listIterator(persons.size());

        while (personsListIterator.hasPrevious()) {
            System.out.println(personsListIterator.previous());
        }
    }

}
