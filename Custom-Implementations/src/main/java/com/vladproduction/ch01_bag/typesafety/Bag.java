package com.vladproduction.ch01_bag.typesafety;

import java.util.Iterator;

public interface Bag<T> extends Iterable<T>{

    boolean isEmpty();

    int size();

    void add(T item);

    Iterator<T> iterator();

    void remove(T item);

    int capacity();

    void clear();

    boolean contains(T item);

}
