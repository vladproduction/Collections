package com.vladproduction.ch06_queue;

import java.util.Iterator;

public interface MyQueue<T> extends Iterable<T>{

    int size();                                 //Get a number of elements

    boolean isEmpty();                          //Quick check for empty state

    void enQueue(T item);                       //Add to the end of the queue

    T deQueue();                                //Remove from front

    T peek();                                   //View front without removing

    void clear();                               //Reset the queue

    boolean contains(T item);                   //Search support (true if found)

    Object[] toArray();                         //Export contents into an array

    Iterator<T> iterator();                     //For-each loop, stream compatibility

}

