package com.vladproduction.app11.generics.genericinterface;

import java.util.ArrayList;
import java.util.List;

public class GenericStorage<T> implements Storage<T> {

    private final List<T> storage;

    public GenericStorage() {
        this.storage = new ArrayList<>();
    }

    @Override
    public void add(T item) {
        if (item != null) {
            storage.add(item);
        } else {
            throw new NullPointerException("Null value is not allowed");
        }
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index must be positive");
        } else if (index >= storage.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return storage.get(index);
    }

    @Override
    public void print() {
        for (T item : storage) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    @Override
    public void forEach(Processor<T> processor) {
        for (T item : storage) {
            processor.process(item);
        }
    }
}
