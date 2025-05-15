package com.vladproduction.app02.list.linkedlist;

public record Person(String name, int age) {

    @Override
    public String toString() {
        return String.format("(%s: %d)", name, age);
    }
}
