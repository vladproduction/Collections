package com.vladproduction.app06.iterable_iterator_listIterator.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomIteratorExample implements Iterable<String>{

    private final String[] data = {"A", "B", "C"};

    @Override
    public Iterator<String> iterator() {

        return new Iterator<>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < data.length;
            }

            @Override
            public String next() {
                if (!hasNext()) throw new NoSuchElementException();
                return data[index++];
            }

        };
    }

    public static void main(String[] args) {

        CustomIteratorExample example = new CustomIteratorExample();
        for (String s : example) {
            System.out.println(s);
        }

    }
}
