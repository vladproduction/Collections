package com.vladproduction.app11.generics.genericinterface;

public interface Storage<T> {

    void add(T item);

    T get(int index);

    void print();

    void forEach(Processor<T> processor);
}
