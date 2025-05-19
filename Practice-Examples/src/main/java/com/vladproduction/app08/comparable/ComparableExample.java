package com.vladproduction.app08.comparable;

import java.util.Arrays;
import java.util.List;

/**
 * Comparable<T> is a functional interface used to define the natural ordering of objects.
 * Implemented by classes that want their instances to be sorted using Collections.sort() or Arrays.sort().
 * Method: int compareTo(T other);
 *  Returns:
 *  - 0 if this == other
 *  - Negative if this < other
 *  - Positive if this > other
 * */
public class ComparableExample {
    public static void main(String[] args) {

        List<Client> clients = Arrays.asList(
                new Client("Alice", 32),
                new Client("Bob", 24),
                new Client("Charlie", 29)
        );

        System.out.println("Before sort:");
        clients.forEach(System.out::println);

        clients.sort(null); // uses compareTo (natural ordering)

        System.out.println("\nAfter sort:");
        clients.forEach(System.out::println);
    }

    static class Client implements Comparable<Client> {
        private final String name;
        private final int age;

        public Client(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // Natural order: by age ascending
        @Override
        public int compareTo(Client other) {
            return Integer.compare(this.age, other.age);
        }

        @Override
        public String toString() {
            return name + " (" + age + ")";
        }
    }
}
