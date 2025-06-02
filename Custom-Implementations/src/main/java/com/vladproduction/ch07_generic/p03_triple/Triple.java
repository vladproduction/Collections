package com.vladproduction.ch07_generic.p03_triple;

/**
 * Generic Triple class with three different types
 * */
public class Triple<T, U, V> {

    private final T first;
    private final U second;
    private final V third;

    public Triple(T first, U second, V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public V getThird() {
        return third;
    }

    @Override
    public String toString() {
        return String.format("Triple: (%s, %s, %s)", first, second, third);
    }

}
