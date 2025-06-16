package com.vladproduction.collectionsframework.ch06_algorithms;

import java.util.*;

/**
 * Demonstrates various sorting techniques and algorithms
 * Covers built-in sorting methods and custom implementations
 */
public class SortingExamples {
    public static void main(String[] args) {

        SortingExamples examples = new SortingExamples();

        System.out.println("=== SORTING EXAMPLES ===");
        System.out.println("=".repeat(50));
        System.out.println();

        examples.demonstrateArraySorting();
        examples.demonstrateListSorting();
        examples.demonstrateCustomObjectSorting();
        examples.demonstrateMultipleSortingCriteria();
        examples.demonstrateStableSorting();

    }
    /**
     * Demonstrates sorting arrays using Arrays.sort()
     */
    private void demonstrateArraySorting() {
        System.out.println("1. Array Sorting Examples:");
        System.out.println("=".repeat(50));

        // Primitive array sorting
        System.out.println("\nPrimitive array sorting");
        int[] numbers = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original: " + Arrays.toString(numbers));
        Arrays.sort(numbers);
        System.out.println("Sorted: " + Arrays.toString(numbers));

        // String array sorting
        System.out.println("\nString array sorting");
        String[] words = {"banana", "apple", "cherry", "date"};
        System.out.println("Original: " + Arrays.toString(words));
        Arrays.sort(words);
        System.out.println("Sorted: " + Arrays.toString(words));

        // Reverse sorting using Collections.reverseOrder()
        System.out.println("\nReverse sorting using Collections.reverseOrder()");
        String[] wordsReverse = {"banana", "apple", "cherry", "date"};
        Arrays.sort(wordsReverse, Collections.reverseOrder());
        System.out.println("Reversed: " + Arrays.toString(wordsReverse));

        // Partial array sorting
        System.out.println("\nPartial array sorting");
        int[] partialSort = {5, 2, 8, 1, 9, 3};
        Arrays.sort(partialSort, 1, 4); //sort this array elements from index 1 to 3
        System.out.println("Partial sort (index 1-3): " + Arrays.toString(partialSort)); //[5, 1, 2, 8, 9, 3]

        System.out.println();

    }

    /**
     * Demonstrates sorting lists using Collections.sort()
     */
    private void demonstrateListSorting() {
        System.out.println("2. List Sorting Examples:");
        System.out.println("=".repeat(50));

        // ArrayList sorting
        System.out.println("\nArrayList sorting:");
        List<Integer> numberList = new ArrayList<>(Arrays.asList(64, 34, 25, 12, 22, 11, 90));
        System.out.println("Original list: " + numberList);
        Collections.sort(numberList);
        System.out.println("Sorted list: " + numberList);

        // Using List.sort() (Java 8+)
        System.out.println("\nUsing List.sort() (Java 8+)");
        List<String> wordList = new ArrayList<>(Arrays.asList("banana", "apple", "cherry", "date"));
        System.out.println("Original list of words: " + wordList);
        wordList.sort(String::compareTo);
        System.out.println("Sorted list of words: " + wordList);

        // Reverse sorting
        System.out.println("\nReverse sorting list of words");
        System.out.println("Before: " + wordList);
        wordList.sort(Collections.reverseOrder());
        System.out.println("Reverse sorted: " + wordList);

        System.out.println();
    }

    /**
     * Demonstrates sorting custom objects
     */
    private void demonstrateCustomObjectSorting() {
        System.out.println("3. Custom Object Sorting:");
        System.out.println("=".repeat(50));

        List<Person> people = Arrays.asList(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Charlie", 35),
                new Person("Diana", 28)
        );

        System.out.println("Original: " + people);

        // Sort by natural ordering (age - defined in Person class)
        Collections.sort(people);
        System.out.println("Sorted by age: " + people);

        // Sort by name using lambda
        people.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        System.out.println("Sorted by name: " + people);

        // Sort using Comparator.comparing()
        people.sort(Comparator.comparing(Person::getAge).reversed());
        System.out.println("Sorted by age (descending): " + people);

        System.out.println();
    }

    /**
     * Demonstrates sorting with multiple criteria
     */
    public void demonstrateMultipleSortingCriteria() {
        System.out.println("4. Multiple Sorting Criteria:");
        System.out.println("=".repeat(50));

        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "Engineering", 75000),
                new Employee("Bob", "Engineering", 80000),
                new Employee("Charlie", "Marketing", 65000),
                new Employee("Diana", "Engineering", 80000),
                new Employee("Eve", "Marketing", 70000)
        );

        System.out.println("Original: " + employees);

        // Sort by department, then by salary (descending), then by name
        employees.sort(Comparator
                .comparing(Employee::getDepartment)
                .thenComparing(Employee::getSalary, Collections.reverseOrder())
                .thenComparing(Employee::getName));

        System.out.println("Sorted by dept, salary(desc), name: " + employees);

        System.out.println();
    }

    /**
     * Demonstrates stable sorting behavior
     */
    public void demonstrateStableSorting() {
        System.out.println("5. Stable Sorting Demo:");
        System.out.println("=".repeat(50));

        List<Card> cards = Arrays.asList(
                new Card("A", "Hearts"),
                new Card("K", "Spades"),
                new Card("A", "Clubs"),
                new Card("K", "Hearts"),
                new Card("A", "Spades")
        );

        System.out.println("Original order: " + cards);

        // Sort by rank only - stable sort preserves original order for equal elements
        cards.sort(Comparator.comparing(Card::getRank));
        System.out.println("Sorted by rank (stable): " + cards);

        System.out.println();
    }


    // Helper classes
    static class Person implements Comparable<Person> {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Person other) {
            return Integer.compare(this.age, other.age);
        }

        public String getName() { return name; }
        public int getAge() { return age; }

        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }

    static class Employee {
        private String name;
        private String department;
        private int salary;

        public Employee(String name, String department, int salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public String getName() { return name; }
        public String getDepartment() { return department; }
        public int getSalary() { return salary; }

        @Override
        public String toString() {
            return name + "(" + department + ",$" + salary + ")";
        }
    }

    static class Card {
        private String rank;
        private String suit;

        public Card(String rank, String suit) {
            this.rank = rank;
            this.suit = suit;
        }

        public String getRank() { return rank; }
        public String getSuit() { return suit; }

        @Override
        public String toString() {
            return rank + suit.charAt(0);
        }
    }


}













