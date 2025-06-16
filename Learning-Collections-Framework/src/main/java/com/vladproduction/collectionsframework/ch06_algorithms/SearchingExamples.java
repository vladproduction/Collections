package com.vladproduction.collectionsframework.ch06_algorithms;

import java.util.*;

/**
 * Demonstrates various searching algorithms and techniques
 * Covers binary search, linear search, and Collections searching methods
 */
public class SearchingExamples {
    public static void main(String[] args) {

        SearchingExamples examples = new SearchingExamples();

        System.out.println("=== SEARCHING EXAMPLES ===");
        System.out.println("=".repeat(50));
        System.out.println();

        examples.demonstrateBinarySearch();
        examples.demonstrateCollectionsBinarySearch();
        examples.demonstrateLinearSearch();
        examples.demonstrateCustomObjectSearch();
        examples.demonstrateSearchingWithComparators();

    }

    /**
     * Demonstrates binary search on arrays using Arrays.binarySearch()
     */
    private void demonstrateBinarySearch() {
        System.out.println("1. Binary Search on Arrays:");
        System.out.println("=".repeat(50));

        // Binary search on sorted primitive array
        System.out.println("\nBinary search on sorted primitive array");
        int[] sortedNumbers = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        System.out.println("Sorted array: " + Arrays.toString(sortedNumbers));

        System.out.println(" - If target is present in array:");
        int target = 7;
        int index = Arrays.binarySearch(sortedNumbers, target);
        System.out.println("Searching for target " + target + " found at index: " + index);

        System.out.println(" - If target not present in array:");
        target = 8;
        index = Arrays.binarySearch(sortedNumbers, target);
        System.out.println(
                "Searching for target " + target + " : " +
                ((index >= 0) ? "found at index " + index : "not found, insertion point is " + (-index-1))
        );

        // Binary search on String array
        System.out.println("\nBinary search on sorted String array");
        String[] sortedWords = {"apple", "banana", "cherry", "date", "elderberry"};
        System.out.println("Sorted words: " + Arrays.toString(sortedWords));

        System.out.println(" - If target is present in array of Strings:");
        String wordTarget = "cherry";
        index = Arrays.binarySearch(sortedWords, wordTarget);
        System.out.println("Searching for '" + wordTarget + "': found at index " + index);

        System.out.println(" - If target not present in array of Strings:");
        wordTarget = "coconut";
        index = Arrays.binarySearch(sortedWords, wordTarget);
        System.out.println(
                "Searching for '" + wordTarget + "': " +
                (index >= 0 ? "found at index " + index : "not found, insertion point is " + (-index - 1))
        );

        // Binary search on array range
        System.out.println("\nBinary search on array range");
        int[] largeArray = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25};
        index = Arrays.binarySearch(largeArray, 2, 8, 9); // Search between index 2 and 7
        System.out.println(
                "Range search for 9 between indexes 2 and 7 (inclusive): " +
                        (index >= 0 ? "found at index " + index : "not found")
        );

        System.out.println();

    }

    /**
     * Demonstrates binary search on Collections using Collections.binarySearch()
     */
    public void demonstrateCollectionsBinarySearch() {
        System.out.println("2. Binary Search on Collections:");
        System.out.println("=".repeat(50));

        // Binary search on sorted List
        System.out.println("\nBinary search on sorted List");
        List<Integer> sortedList = Arrays.asList(1, 3, 5, 7, 9, 11, 13, 15, 17, 19);
        System.out.println("Sorted list: " + sortedList);

        System.out.println(" - If target is present in Collections:");
        int target = 11;
        int index = Collections.binarySearch(sortedList, target);
        System.out.println("Searching for " + target + ": found at index " + index);

        System.out.println(" - If target not present in Collections:");
        target = 12;
        index = Collections.binarySearch(sortedList, target);
        System.out.println("Searching for " + target + ": " +
                (index >= 0 ? "found at index " + index :
                        "not found, insertion point: " + (-index - 1)));

        // Binary search with custom comparator
        System.out.println("\nBinary search with custom comparator");
        List<String> caseInsensitiveWords = Arrays.asList("Apple", "banana", "Cherry", "date");
        caseInsensitiveWords.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println("Case-insensitive sorted: " + caseInsensitiveWords);

        index = Collections.binarySearch(caseInsensitiveWords, "BANANA", String.CASE_INSENSITIVE_ORDER);
        System.out.println("Case-insensitive search for 'BANANA': found at index " + index);

        System.out.println();
    }

    /**
     * Demonstrates linear search implementation and usage
     */
    public void demonstrateLinearSearch() {
        System.out.println("3. Linear Search Examples:");
        System.out.println("=".repeat(50));

        // Linear search on unsorted array
        System.out.println("\nLinear search on unsorted array");
        int[] unsortedNumbers = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Unsorted array: " + Arrays.toString(unsortedNumbers));

        int target = 22;
        int index = linearSearch(unsortedNumbers, target);
        System.out.println("Linear search for " + target + ": " +
                (index != -1 ? "found at index " + index : "not found"));

        // Linear search using contains() method
        System.out.println("\nLinear search using contains() method");
        List<String> wordList = Arrays.asList("hello", "world", "java", "programming");
        System.out.println("Word list: " + wordList);

        String searchWord = "java";
        boolean found = wordList.contains(searchWord);
        System.out.println("Contains '" + searchWord + "': " + found);

        // Linear search with indexOf()
        System.out.println("\nLinear search with indexOf()");
        int wordIndex = wordList.indexOf(searchWord);
        System.out.println("Index of '" + searchWord + "': " + wordIndex);

        // Finding all occurrences
        System.out.println("\nFinding all occurrences");
        List<String> duplicateWords = Arrays.asList("apple", "banana", "apple", "cherry", "apple", "apple");
        System.out.println("List with duplicates: " + duplicateWords);
        List<Integer> appleIndices = findAllOccurrences(duplicateWords, "apple");
        System.out.println("All indices of 'apple': " + appleIndices);

        System.out.println();
    }

    /**
     * Demonstrates searching custom objects
     */
    public void demonstrateCustomObjectSearch() {
        System.out.println("4. Custom Object Search:");
        System.out.println("=".repeat(50));

        List<Book> books = Arrays.asList(
                new Book("1984", "George Orwell", 1949),
                new Book("To Kill a Mockingbird", "Harper Lee", 1960),
                new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925),
                new Book("Pride and Prejudice", "Jane Austen", 1813)
        );

        System.out.println("Books: " + books);

        // Sort by title for binary search
        books.sort(Comparator.comparing(Book::getTitle));
        System.out.println("Sorted by title: " + books);

        // Binary search for book by title
        System.out.println("\nBinary search for book by title");
        Book searchBook = new Book("1984", "", 0);
        int index = Collections.binarySearch(books, searchBook, Comparator.comparing(Book::getTitle));
        System.out.println("Binary search for '1984': " +
                (index >= 0 ? "found at index " + index : "not found"));

        // Linear search using custom predicate
        System.out.println("\nLinear search using custom predicate");
        Book foundBook = findBookByAuthor(books, "Harper Lee");
        System.out.println("Book by Harper Lee: " + foundBook);

        // Find books published after certain year
        System.out.println("\nFind books published after certain year (1950)");
        List<Book> modernBooks = findBooksAfterYear(books, 1950);
        System.out.println("Books after 1950: " + modernBooks);

        System.out.println();
    }

    /**
     * Demonstrates searching with different comparators
     */
    public void demonstrateSearchingWithComparators() {
        System.out.println("5. Searching with Comparators:");
        System.out.println("=".repeat(50));

        List<Student> students = Arrays.asList(
                new Student("Alice", 85.5),
                new Student("Bob", 92.0),
                new Student("Charlie", 78.5),
                new Student("Diana", 95.5)
        );

        // Sort by grade for binary search
        students.sort(Comparator.comparing(Student::getGrade));
        System.out.println("Students sorted by grade: " + students);

        // Binary search by grade
        System.out.println("\nBinary search by grade");
        Student searchStudent = new Student("", 92.0);
        int index = Collections.binarySearch(students, searchStudent,
                Comparator.comparing(Student::getGrade));
        System.out.println("Student with grade 92.0: " +
                (index >= 0 ? students.get(index) : "not found"));

        // Custom comparator for case-insensitive name search
        System.out.println("\nCustom comparator for case-insensitive name search");
        students.sort(Comparator.comparing(student -> student.getName().toLowerCase()));
        System.out.println("Students sorted by name (case-insensitive): " + students);

        Student nameSearch = new Student("ALICE", 0);
        Comparator<Student> nameComparator = Comparator.comparing(
                student -> student.getName().toLowerCase());
        index = Collections.binarySearch(students, nameSearch, nameComparator);
        System.out.println("Case-insensitive search for 'ALICE': " +
                (index >= 0 ? students.get(index) : "not found"));

        System.out.println();
    }

    // Helper methods for linear search
    private int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    private List<Integer> findAllOccurrences(List<String> list, String target) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(target)) {
                indices.add(i);
            }
        }
        return indices;
    }

    private Book findBookByAuthor(List<Book> books, String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equals(author))
                .findFirst()
                .orElse(null);
    }

    private List<Book> findBooksAfterYear(List<Book> books, int year) {
        return books.stream()
                .filter(book -> book.getYear() > year)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    // Helper classes
    static class Book {

        private String title;
        private String author;
        private int year;

        public Book(String title, String author, int year) {
            this.title = title;
            this.author = author;
            this.year = year;
        }

        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public int getYear() { return year; }

        @Override
        public String toString() {
            return title + " by " + author + " (" + year + ")";
        }
    }

    static class Student {

        private String name;
        private double grade;

        public Student(String name, double grade) {
            this.name = name;
            this.grade = grade;
        }

        public String getName() { return name; }
        public double getGrade() { return grade; }

        @Override
        public String toString() {
            return name + "(" + grade + ")";
        }
    }

}
