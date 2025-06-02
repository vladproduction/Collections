package com.vladproduction.ch02_arraylist;

import java.util.Iterator;

public interface MyArrayList<T> extends Iterable<T>{

    boolean isEmpty();

    int size();

    void add(T item);

    T getByIndex(int index);

    T setByIndex(int index, T newItem);

    boolean contains(T item);

    void clear();

    int capacity();

    void removeByIndex(int index);

    Iterator<T> iterator();
}
