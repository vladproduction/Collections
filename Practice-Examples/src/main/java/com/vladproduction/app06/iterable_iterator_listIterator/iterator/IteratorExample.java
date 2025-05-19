package com.vladproduction.app06.iterable_iterator_listIterator.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Iterator<E> is used to traverse collections (List, Set, etc.) sequentially.
 * Methods: boolean hasNext(); E next(); void remove(); // optional
 * Key Points:
 *  - Iterators are fail-fast: if the collection is modified structurally outside the iterator, ConcurrentModificationException is thrown.
 *  - Must call next() before calling remove(); otherwise, --> get IllegalStateException.
 */
public class IteratorExample {
    public static void main(String[] args) {

        List<String> fruits = new ArrayList<>();
        fruits.add("apple");
        fruits.add("banana");
        fruits.add("cherry");

        Iterator<String> iterator = fruits.iterator();

        System.out.println("Original list:");
        while (iterator.hasNext()) {
            String fruit = iterator.next();
            System.out.println(fruit);
        }

        // Remove elements that start with 'b'
        iterator = fruits.iterator(); // Reset iterator
        while (iterator.hasNext()) {
            String fruit = iterator.next();
            if (fruit.startsWith("b")) {
                iterator.remove(); // safe remove
            }
        }

        System.out.println("After removal by using iterator:");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
    }
}
