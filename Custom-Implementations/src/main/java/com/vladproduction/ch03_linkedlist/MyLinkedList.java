package com.vladproduction.ch03_linkedlist;

import java.util.Iterator;

public interface MyLinkedList<T> extends Iterable<T>{

    boolean isEmpty();

    int size();

    void add(T item);

    T getByIndex(int index);

    T setByIndex(int index, T newItem);

    boolean contains(T item);

    void clear();

    void removeByIndex(int index);

    Iterator<T> iterator();
}


