package com.vladproduction.collectionsframework.ch06_algorithms;

import java.util.*;

/**
 * Demonstrates custom comparators and Comparable implementations
 * Covers various ways to define custom sorting logic
 */
public class CustomComparators {
    public static void main(String[] args) {

        CustomComparators examples = new CustomComparators();

        System.out.println("=== CUSTOM COMPARATORS & COMPARABLE ===");
        System.out.println("=======================================\n");

        examples.demonstrateComparableInterface();
        examples.demonstrateComparatorInterface();
        examples.demonstrateLambdaComparators();
        examples.demonstrateComparatorChaining();
        examples.demonstrateComparatorUtilityMethods();
        examples.demonstrateAdvancedComparatorTechniques();
        examples.demonstrateRealWorldExamples();

    }

    /**
     * Demonstrates implementing Comparable interface
     */
    public void demonstrateComparableInterface() {
        System.out.println("1. Comparable Interface Implementation:");
        System.out.println("=".repeat(50));

        List<Product> products = Arrays.asList(
                new Product("Laptop", 999.99),
                new Product("Mouse", 25.50),
                new Product("Keyboard", 75.00),
                new Product("Monitor", 299.99)
        );

        System.out.println("Original products: " + products);

        // Natural sorting using Comparable (by price)
        Collections.sort(products);
        System.out.println("Sorted by price (natural order): " + products);

        // Using TreeSet with natural ordering
        Set<Product> productSet = new TreeSet<>(products);
        System.out.println("TreeSet (natural order): " + productSet);

        System.out.println();
    }

    /**
     * Demonstrates Comparator interface implementations
     */
    public void demonstrateComparatorInterface() {
        System.out.println("2. Comparator Interface Implementation:");
        System.out.println("=".repeat(50));

        List<Student> students = Arrays.asList(
                new Student("Alice", 85, "Computer Science"),
                new Student("Bob", 92, "Mathematics"),
                new Student("Charlie", 78, "Physics"),
                new Student("Diana", 95, "Computer Science")
        );

        System.out.println("Original students: " + students);

        // Sort by name using anonymous class
        students.sort(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getName().compareTo(s2.getName());
            }
        });
        System.out.println("Sorted by name (anonymous class): " + students);

        // Sort by grade using separate comparator class
        students.sort(new GradeComparator());
        System.out.println("Sorted by grade (separate class): " + students);

        // Sort by major using static nested class
        students.sort(new MajorComparator());
        System.out.println("Sorted by major (nested class): " + students);

        System.out.println();
    }

    /**
     * Demonstrates lambda expressions and method references for comparators
     */
    public void demonstrateLambdaComparators() {
        System.out.println("3. Lambda Expression Comparators:");
        System.out.println("=".repeat(50));

        List<Employee> employees = Arrays.asList(
                new Employee("John", "Engineering", 75000, 5),
                new Employee("Jane", "Marketing", 65000, 3),
                new Employee("Mike", "Engineering", 80000, 7),
                new Employee("Sarah", "HR", 60000, 2)
        );

        System.out.println("Original employees: " + employees);

        // Lambda expression comparators
        employees.sort((e1, e2) -> e1.getName().compareTo(e2.getName()));
        System.out.println("Sorted by name (lambda): " + employees);

        employees.sort((e1, e2) -> Integer.compare(e1.getSalary(), e2.getSalary()));
        System.out.println("Sorted by salary (lambda): " + employees);

        // Method reference comparators
        employees.sort(Comparator.comparing(Employee::getDepartment));
        System.out.println("Sorted by department (method ref): " + employees);

        employees.sort(Comparator.comparing(Employee::getYearsOfExperience));
        System.out.println("Sorted by experience (method ref): " + employees);

        // Reverse ordering
        employees.sort(Comparator.comparing(Employee::getSalary).reversed());
        System.out.println("Sorted by salary (descending): " + employees);

        System.out.println();
    }

    /**
     * Demonstrates chaining comparators for multi-level sorting
     */
    public void demonstrateComparatorChaining() {
        System.out.println("4. Comparator Chaining:");
        System.out.println("=".repeat(50));

        List<Employee> employees = Arrays.asList(
                new Employee("John", "Engineering", 75000, 5),
                new Employee("Jane", "Engineering", 75000, 3),
                new Employee("Mike", "Engineering", 80000, 7),
                new Employee("Sarah", "Marketing", 65000, 2),
                new Employee("Tom", "Marketing", 65000, 4)
        );

        System.out.println("Original employees: " + employees);

        // Chain comparators: department, then salary (desc), then experience (desc), then name
        employees.sort(Comparator
                .comparing(Employee::getDepartment)
                .thenComparing(Employee::getSalary, Collections.reverseOrder())
                .thenComparing(Employee::getYearsOfExperience, Collections.reverseOrder())
                .thenComparing(Employee::getName));

        System.out.println("Multi-level sort: " + employees);

        // Alternative chaining syntax
        List<Employee> employees2 = Arrays.asList(
                new Employee("Alice", "IT", 70000, 4),
                new Employee("Bob", "IT", 70000, 6),
                new Employee("Charlie", "Finance", 60000, 3)
        );

        employees2.sort(Comparator
                .comparing(Employee::getDepartment)
                .thenComparingInt(Employee::getSalary)
                .thenComparingInt(Employee::getYearsOfExperience));

        System.out.println("Alternative chaining: " + employees2);

        System.out.println();
    }

    /**
     * Demonstrates Comparator utility methods
     */
    public void demonstrateComparatorUtilityMethods() {
        System.out.println("5. Comparator Utility Methods:");
        System.out.println("=".repeat(50));

        List<String> words = Arrays.asList("apple", "Banana", "cherry", "Date", null, "elderberry");
        System.out.println("Original words: " + words);

        // Natural order comparator
        List<String> naturalOrder = new ArrayList<>(words);
        naturalOrder.removeIf(Objects::isNull); // Remove null for natural ordering
        naturalOrder.sort(Comparator.naturalOrder());
        System.out.println("Natural order: " + naturalOrder);

        // Reverse order comparator
        List<String> reverseOrder = new ArrayList<>(naturalOrder);
        reverseOrder.sort(Comparator.reverseOrder());
        System.out.println("Reverse order: " + reverseOrder);

        // Null-safe comparators
        List<String> withNulls = new ArrayList<>(words);
        withNulls.sort(Comparator.nullsFirst(Comparator.naturalOrder()));
        System.out.println("Nulls first: " + withNulls);

        withNulls.sort(Comparator.nullsLast(Comparator.naturalOrder()));
        System.out.println("Nulls last: " + withNulls);

        // Case-insensitive comparison
        List<String> caseInsensitive = Arrays.asList("apple", "Banana", "cherry", "Date");
        caseInsensitive.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println("Case insensitive: " + caseInsensitive);

        // Comparing with key extractor
        List<String> byLength = Arrays.asList("apple", "hi", "banana", "cat");
        byLength.sort(Comparator.comparing(String::length));
        System.out.println("By length: " + byLength);

        // Comparing with custom key extractor
        byLength.sort(Comparator.comparing(s -> s.charAt(0)));
        System.out.println("By first character: " + byLength);

        System.out.println();
    }

    /**
     * Demonstrates advanced comparator techniques
     */
    public void demonstrateAdvancedComparatorTechniques() {
        System.out.println("6. Advanced Comparator Techniques:");
        System.out.println("=".repeat(50));

        List<Book> books = Arrays.asList(
                new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, 4.0),
                new Book("To Kill a Mockingbird", "Harper Lee", 1960, 4.3),
                new Book("1984", "George Orwell", 1949, 4.2),
                new Book("Pride and Prejudice", "Jane Austen", 1813, 4.1)
        );

        System.out.println("Original books: " + books);

        // Custom comparator with complex logic
        Comparator<Book> complexComparator = (b1, b2) -> {
            // First compare by rating (descending)
            int ratingCompare = Double.compare(b2.getRating(), b1.getRating());
            if (ratingCompare != 0) return ratingCompare;

            // Then by publication year (descending for modern books)
            int yearCompare = Integer.compare(b2.getYear(), b1.getYear());
            if (yearCompare != 0) return yearCompare;

            // Finally by title
            return b1.getTitle().compareTo(b2.getTitle());
        };

        books.sort(complexComparator);
        System.out.println("Complex sort (rating desc, year desc, title asc): " + books);

        // Comparator with conditional logic
        Comparator<Book> conditionalComparator = Comparator
                .comparing(Book::getRating, Collections.reverseOrder())
                .thenComparing(book -> book.getYear() > 1950 ? 0 : 1) // Modern books first
                .thenComparing(Book::getTitle);

        books.sort(conditionalComparator);
        System.out.println("Conditional sort (rating desc, modern first, title): " + books);

        // Using Comparator.comparing with custom key extractors
        books.sort(Comparator.comparing(book -> book.getAuthor().split(" ")[1])); // Sort by last name
        System.out.println("Sorted by author's last name: " + books);

        System.out.println();
    }

    /**
     * Demonstrates real-world comparator examples
     */
    public void demonstrateRealWorldExamples() {
        System.out.println("7. Real-World Comparator Examples:");
        System.out.println("=".repeat(50));

        List<Task> tasks = Arrays.asList(
                new Task("Deploy to production", Priority.HIGH, "2024-01-15"),
                new Task("Write unit tests", Priority.MEDIUM, "2024-01-10"),
                new Task("Code review", Priority.HIGH, "2024-01-12"),
                new Task("Update documentation", Priority.LOW, "2024-01-08"),
                new Task("Fix critical bug", Priority.CRITICAL, "2024-01-14")
        );

        System.out.println("Original tasks: " + tasks);

        // Sort by priority (using enum ordinal) then by due date
        tasks.sort(Comparator
                .comparing(Task::getPriority)
                .thenComparing(Task::getDueDate));

        System.out.println("Sorted by priority and due date: " + tasks);

        // E-commerce product sorting example
        List<Product> products = Arrays.asList(
                new Product("Laptop Pro", 1299.99, 4.5, 150),
                new Product("Laptop Basic", 699.99, 4.0, 300),
                new Product("Desktop Pro", 899.99, 4.3, 75),
                new Product("Tablet", 399.99, 4.2, 200)
        );

        System.out.println("Original products: " + products);

        // E-commerce sorting: rating desc, then popularity (stock), then price asc
        Comparator<Product> ecommerceSort = Comparator
                .comparing(Product::getRating, Collections.reverseOrder())
                .thenComparing(Product::getStock, Collections.reverseOrder())
                .thenComparingDouble(Product::getPrice);

        products.sort(ecommerceSort);
        System.out.println("E-commerce sort (rating, popularity, price): " + products);

        // Geographic sorting example
        List<City> cities = Arrays.asList(
                new City("New York", "NY", 8336000),
                new City("Los Angeles", "CA", 3979000),
                new City("Albany", "NY", 97000),
                new City("Sacramento", "CA", 525000)
        );

        System.out.println("Original cities: " + cities);

        // Sort by state, then by population descending
        cities.sort(Comparator
                .comparing(City::getState)
                .thenComparing(City::getPopulation, Collections.reverseOrder()));

        System.out.println("Sorted by state, then population: " + cities);

        System.out.println();
    }


    // Helper classes and comparators

    // Product class implementing Comparable
    static class Product implements Comparable<Product> {
        private String name;
        private double price;
        private double rating;
        private int stock;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
            this.rating = 0.0;
            this.stock = 0;
        }

        public Product(String name, double price, double rating, int stock) {
            this.name = name;
            this.price = price;
            this.rating = rating;
            this.stock = stock;
        }

        @Override
        public int compareTo(Product other) {
            return Double.compare(this.price, other.price);
        }

        public String getName() { return name; }
        public double getPrice() { return price; }
        public double getRating() { return rating; }
        public int getStock() { return stock; }

        @Override
        public String toString() {
            return name + "($" + price + (rating > 0 ? ", " + rating + "★, stock:" + stock : "") + ")";
        }
    }

    // Student class for examples
    static class Student {
        private String name;
        private int grade;
        private String major;

        public Student(String name, int grade, String major) {
            this.name = name;
            this.grade = grade;
            this.major = major;
        }

        public String getName() { return name; }
        public int getGrade() { return grade; }
        public String getMajor() { return major; }

        @Override
        public String toString() {
            return name + "(" + grade + "," + major + ")";
        }
    }

    // Employee class for examples
    static class Employee {
        private String name;
        private String department;
        private int salary;
        private int yearsOfExperience;

        public Employee(String name, String department, int salary, int yearsOfExperience) {
            this.name = name;
            this.department = department;
            this.salary = salary;
            this.yearsOfExperience = yearsOfExperience;
        }

        public String getName() { return name; }
        public String getDepartment() { return department; }
        public int getSalary() { return salary; }
        public int getYearsOfExperience() { return yearsOfExperience; }

        @Override
        public String toString() {
            return name + "(" + department + ",$" + salary + "," + yearsOfExperience + "y)";
        }
    }

    // Book class for examples
    static class Book {
        private String title;
        private String author;
        private int year;
        private double rating;

        public Book(String title, String author, int year, double rating) {
            this.title = title;
            this.author = author;
            this.year = year;
            this.rating = rating;
        }

        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public int getYear() { return year; }
        public double getRating() { return rating; }

        @Override
        public String toString() {
            return title + " by " + author + " (" + year + ", " + rating + "★)";
        }
    }

    // Task class with Priority enum
    enum Priority {
        LOW, MEDIUM, HIGH, CRITICAL
    }

    static class Task {
        private String description;
        private Priority priority;
        private String dueDate;

        public Task(String description, Priority priority, String dueDate) {
            this.description = description;
            this.priority = priority;
            this.dueDate = dueDate;
        }

        public String getDescription() { return description; }
        public Priority getPriority() { return priority; }
        public String getDueDate() { return dueDate; }

        @Override
        public String toString() {
            return description + " [" + priority + ", " + dueDate + "]";
        }
    }

    // City class for geographic sorting
    static class City {
        private String name;
        private String state;
        private int population;

        public City(String name, String state, int population) {
            this.name = name;
            this.state = state;
            this.population = population;
        }

        public String getName() { return name; }
        public String getState() { return state; }
        public int getPopulation() { return population; }

        @Override
        public String toString() {
            return name + ", " + state + " (" + population + ")";
        }
    }

    // Separate comparator classes
    static class GradeComparator implements Comparator<Student> {
        @Override
        public int compare(Student s1, Student s2) {
            return Integer.compare(s2.getGrade(), s1.getGrade()); // Descending order
        }
    }

    static class MajorComparator implements Comparator<Student> {
        @Override
        public int compare(Student s1, Student s2) {
            return s1.getMajor().compareTo(s2.getMajor());
        }
    }

}
