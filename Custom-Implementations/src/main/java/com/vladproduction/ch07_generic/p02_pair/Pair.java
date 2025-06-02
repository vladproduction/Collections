package com.vladproduction.ch07_generic.p02_pair;

/**
 * Generic Pair class that can hold any two different types
 * */
public class Pair<T, U> {

    private final T first;
    private final U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return String.format("Pair: (%s, %s)", first, second);
    }
}
