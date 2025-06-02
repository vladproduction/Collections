package com.vladproduction.ch07_generic.p01_container;

/**
 * Container class for generic types can hold any type
 * */
public class Container<T> {

    private T item;

    public Container(T item){
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return String .format("Container: %s", item);
    }
}
