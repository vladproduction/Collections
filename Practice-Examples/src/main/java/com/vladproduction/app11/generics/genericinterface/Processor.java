package com.vladproduction.app11.generics.genericinterface;

@FunctionalInterface
public interface Processor<T> {

    void process(T item);

}
