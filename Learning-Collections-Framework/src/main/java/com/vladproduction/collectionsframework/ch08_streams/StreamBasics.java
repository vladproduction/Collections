package com.vladproduction.collectionsframework.ch08_streams;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

/**
 * StreamBasics - Introduction to Java Stream API and Functional Programming
 *
 * This class demonstrates the fundamentals of Java 8+ Stream API, which provides
 * a powerful way to process collections of data using functional programming concepts.
 *
 * Key Concepts Covered:
 * - Stream creation and basic operations
 * - Lambda expressions and method references
 * - Intermediate vs Terminal operations
 * - Common stream operations (filter, map, reduce, etc.)
 * - Functional interfaces (Predicate, Function, Consumer, Supplier)
 *
 * Learning Objectives:
 * - Understand the Stream API paradigm
 * - Master lambda expressions and method references
 * - Learn the difference between intermediate and terminal operations
 * - Practice functional programming patterns
 *
 * @author vladproduction
 * @version 1.0
 * @since Java 8
 */
public class StreamBasics {
    public static void main(String[] args) {
        System.out.println("=== Java Stream API Basics ===".toUpperCase());
        System.out.println("=".repeat(50));
        System.out.println();

        // Demonstrate stream creation methods
        demonstrateStreamCreation();

        // Show intermediate operations
        demonstrateIntermediateOperations();

        // Show terminal operations
        demonstrateTerminalOperations();

        // Lambda expressions examples
        demonstrateLambdaExpressions();

        // Method references examples
        demonstrateMethodReferences();

        // Functional interfaces examples
        demonstrateFunctionalInterfaces();

        // Complex stream pipeline example
        demonstrateComplexPipeline();
    }

    /**
     * Demonstrates various ways to create streams
     *
     * Stream Creation Methods:
     * - From collections (.stream())
     * - From arrays (Arrays.stream())
     * - From values (Stream.of())
     * - From ranges (IntStream.range())
     * - From generators (Stream.generate())
     * - From iterators (Stream.iterate())
     */
    private static void demonstrateStreamCreation() {
        System.out.println("1. Stream Creation Methods:");
        System.out.println("=".repeat(50));

        // 1. From Collection
        List<String> cities = Arrays.asList("Paris", "London", "Tokyo", "New York");
        Stream<String> cityStream = cities.stream();
        System.out.println("From Collection: " + cities);

        // 2. From Array
        String[] colors = {"Red", "Green", "Blue", "Yellow"};
        Stream<String> colorStream = Arrays.stream(colors);
        System.out.println("From Array: " + Arrays.toString(colors));

        // 3. From Values
        Stream<Integer> numberStream = Stream.of(1, 2, 3, 4, 5);
        System.out.println("From Values: 1, 2, 3, 4, 5");

        // 4. From Range
        IntStream rangeStream = IntStream.range(1, 6);
        System.out.println("From Range: " + rangeStream.boxed().toList());

        // 5. From Generator
        Stream<Double> randomStream = Stream.generate(Math::random).limit(3);
        System.out.println("From Generator (3 random): " + randomStream.toList());

        // 6. From Iterator
        Stream<Integer> fibonacciStream = Stream.iterate(new int[]{0, 1},
                        arr -> new int[]{arr[1], arr[0] + arr[1]})
                .map(arr -> arr[0])
                .limit(5);
        System.out.println("From Iterator (Fibonacci): " + fibonacciStream.toList());

        System.out.println();
    }

    /**
     * Demonstrates intermediate operations
     *
     * Intermediate operations are lazy - they don't execute until a terminal operation is called.
     * They return a new Stream, allowing for method chaining.
     *
     * Common Intermediate Operations:
     * - filter(): Keep elements that match a predicate
     * - map(): Transform elements using a function
     * - flatMap(): Flatten nested structures
     * - distinct(): Remove duplicates
     * - sorted(): Sort elements
     * - peek(): Perform action without modifying stream
     * - limit(): Limit number of elements
     * - skip(): Skip first n elements
     */
    private static void demonstrateIntermediateOperations() {
        System.out.println("2. Intermediate Operations:");
        System.out.println("=".repeat(50));

        List<String> words = Arrays.asList("java", "stream", "lambda", "functional", "programming");

        // filter() - Keep only words longer than 4 characters
        List<String> longWords = words.stream()
                .filter(word -> word.length() > 4)
                .toList();
        System.out.println("Words > 4 chars: " + longWords);

        // map() - Transform to uppercase
        List<String> upperWords = words.stream()
                .map(String::toUpperCase)
                .toList();
        System.out.println("Uppercase: " + upperWords);

        // flatMap() - Flatten character streams
        List<Character> allChars = words.stream()
                .flatMap(word -> word.chars().mapToObj(c -> (char) c))
                .distinct()
                .sorted()
                .toList();
        System.out.println("All unique chars: " + allChars);

        // sorted() and limit()
        List<String> sortedLimited = words.stream()
                .sorted()
                .limit(3)
                .toList();
        System.out.println("First 3 sorted: " + sortedLimited);

        // peek() - Debug stream processing
        List<String> debugWords = words.stream()
                .peek(word -> System.out.println("  Processing: " + word))
                .filter(word -> word.startsWith("f"))
                .map(String::toUpperCase)
                .toList();
        System.out.println("Words starting with 'f': " + debugWords);

        System.out.println();
    }

    /**
     * Demonstrates terminal operations
     *
     * Terminal operations trigger the execution of the stream pipeline and produce a result.
     * After a terminal operation, the stream is consumed and cannot be reused.
     *
     * Common Terminal Operations:
     * - collect(): Collect into a collection
     * - forEach(): Perform action on each element
     * - reduce(): Reduce to a single value
     * - findFirst()/findAny(): Find elements
     * - anyMatch()/allMatch()/noneMatch(): Boolean conditions
     * - count(): Count elements
     * - min()/max(): Find minimum/maximum
     */
    private static void demonstrateTerminalOperations() {
        System.out.println("3. Terminal Operations:");
        System.out.println("=".repeat(50));

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // collect() - Collect even numbers into a Set
        Set<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toSet());
        System.out.println("Even numbers: " + evenNumbers);

        // reduce() - Sum all numbers
        int sum = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println("Sum: " + sum);

        // reduce() - Find maximum
        Optional<Integer> max = numbers.stream()
                .reduce(Integer::max);
        System.out.println("Maximum: " + max.orElse(0));

        // findFirst() - Find first even number
        Optional<Integer> firstEven = numbers.stream()
                .filter(n -> n % 2 == 0)
                .findFirst();
        System.out.println("First even: " + firstEven.orElse(-1));

        // Matching operations
        boolean anyGreaterThan5 = numbers.stream().anyMatch(n -> n > 5);
        boolean allPositive = numbers.stream().allMatch(n -> n > 0);
        boolean noneNegative = numbers.stream().noneMatch(n -> n < 0);

        System.out.println("Any > 5: " + anyGreaterThan5);
        System.out.println("All positive: " + allPositive);
        System.out.println("None negative: " + noneNegative);

        // count()
        long evenCount = numbers.stream()
                .filter(n -> n % 2 == 0)
                .count();
        System.out.println("Even count: " + evenCount);

        System.out.println();
    }

    /**
     * Demonstrates lambda expressions
     *
     * Lambda expressions provide a concise way to represent anonymous functions.
     * Syntax: (parameters) -> expression or (parameters) -> { statements; }
     *
     * Types of Lambda Expressions:
     * - No parameters: () -> expression
     * - Single parameter: param -> expression
     * - Multiple parameters: (param1, param2) -> expression
     * - Block body: (params) -> { multiple statements; return value; }
     */
    private static void demonstrateLambdaExpressions() {
        System.out.println("4. Lambda Expressions:");
        System.out.println("=".repeat(50));

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Diana", "Eve");

        // Simple lambda - no parameters
        Supplier<String> greeting = () -> "Hello, World!";
        System.out.println("No params: " + greeting.get());

        // Single parameter lambda
        names.stream()
                .filter(name -> name.length() > 3)
                .forEach(name -> System.out.println("Long name: " + name));

        // Multiple parameters lambda
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int product = numbers.stream()
                .reduce(1, (a, b) -> a * b);
        System.out.println("Product: " + product);

        // Block body lambda
        List<String> processedNames = names.stream()
                .map(name -> {
                    String processed = name.toUpperCase();
                    if (processed.length() > 4) {
                        processed = processed + "!";
                    }
                    return processed;
                })
                .toList();
        System.out.println("Processed names: " + processedNames);

        // Lambda with custom functional interface
        MathOperation addition = (a, b) -> a + b;
        MathOperation multiplication = (a, b) -> a * b;

        System.out.println("Addition: " + performOperation(5, 3, addition));
        System.out.println("Multiplication: " + performOperation(5, 3, multiplication));

        System.out.println();
    }

    /**
     * Demonstrates method references
     *
     * Method references provide a way to refer to methods without executing them.
     * They are a shorthand for lambda expressions that only call a single method.
     *
     * Types of Method References:
     * - Static method reference: ClassName::methodName
     * - Instance method reference: instance::methodName
     * - Constructor reference: ClassName::new
     * - Method reference to instance method of arbitrary object: ClassName::methodName
     */
    private static void demonstrateMethodReferences() {
        System.out.println("5. Method References:");
        System.out.println("=".repeat(50));

        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6);

        // Static method reference
        List<Integer> lengths = words.stream()
                .map(String::length)  // Equivalent to: word -> word.length()
                .toList();
        System.out.println("Lengths (String::length): " + lengths);

        // Instance method reference
        words.stream()
                .map(String::toUpperCase)  // Equivalent to: word -> word.toUpperCase()
                .forEach(System.out::println);  // Equivalent to: word -> System.out.println(word)

        // Constructor reference
        List<StringBuilder> builders = words.stream()
                .map(StringBuilder::new)  // Equivalent to: word -> new StringBuilder(word)
                .toList();
        System.out.println("StringBuilder count: " + builders.size());

        // Method reference vs lambda comparison
        System.out.println("\nMethod Reference vs Lambda:");

        // Using method reference
        numbers.stream()
                .sorted(Integer::compareTo)
                .forEach(System.out::print);
        System.out.println(" (Method Reference)");

        // Using lambda (equivalent)
        numbers.stream()
                .sorted((a, b) -> a.compareTo(b))
                .forEach(n -> System.out.print(n));
        System.out.println(" (Lambda)");

        System.out.println();
    }

    /**
     * Demonstrates built-in functional interfaces
     *
     * Java provides several built-in functional interfaces in java.util.function package:
     * - Predicate<T>: T -> boolean (test conditions)
     * - Function<T,R>: T -> R (transform data)
     * - Consumer<T>: T -> void (consume data)
     * - Supplier<T>: () -> T (supply data)
     * - BinaryOperator<T>: (T,T) -> T (combine two values)
     * - UnaryOperator<T>: T -> T (transform same type)
     */
    private static void demonstrateFunctionalInterfaces() {
        System.out.println("6. Functional Interfaces:");
        System.out.println("=".repeat(50));

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Predicate<T> - Test conditions
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isGreaterThan5 = n -> n > 5;
        Predicate<Integer> isEvenAndGreaterThan5 = isEven.and(isGreaterThan5);

        List<Integer> filteredNumbers = numbers.stream()
                .filter(isEvenAndGreaterThan5)
                .toList();
        System.out.println("Even and > 5: " + filteredNumbers);

        // Function<T,R> - Transform data
        Function<String, Integer> stringLength = String::length;
        Function<Integer, String> intToString = Object::toString;
        Function<String, String> lengthToString = stringLength.andThen(intToString);

        List<String> words = Arrays.asList("Java", "Stream", "API");
        List<String> lengthStrings = words.stream()
                .map(lengthToString)
                .toList();
        System.out.println("Length strings: " + lengthStrings);

        // Consumer<T> - Consume data
        Consumer<String> printer = System.out::println;
        Consumer<String> upperPrinter = s -> System.out.println(s.toUpperCase());
        Consumer<String> combinedPrinter = printer.andThen(upperPrinter);

        System.out.println("Consumer example:");
        words.stream().forEach(combinedPrinter);

        // Supplier<T> - Supply data
        Supplier<String> randomWord = () -> {
            String[] wordArray = {"Hello", "World", "Java", "Stream"};
            return wordArray[new Random().nextInt(wordArray.length)];
        };

        List<String> randomWords = Stream.generate(randomWord)
                .limit(3)
                .toList();
        System.out.println("Random words: " + randomWords);

        // BinaryOperator<T> - Combine two values
        BinaryOperator<Integer> sum = Integer::sum;
        BinaryOperator<Integer> max = Integer::max;

        Optional<Integer> totalSum = numbers.stream().reduce(sum);
        Optional<Integer> maximum = numbers.stream().reduce(max);

        System.out.println("Sum: " + totalSum.orElse(0));
        System.out.println("Max: " + maximum.orElse(0));

        System.out.println();
    }

    /**
     * Demonstrates a complex stream pipeline combining multiple operations
     *
     * This example shows how to chain multiple stream operations together
     * to solve a real-world problem: analyzing employee data.
     */
    private static void demonstrateComplexPipeline() {
        System.out.println("7. Complex Stream Pipeline:");
        System.out.println("=".repeat(50));

        // Sample employee data
        List<Employee> employees = createEmployeeData();

        // Complex pipeline: Find top 3 highest-paid employees in IT department
        // who joined after 2020, sorted by salary descending
        List<Employee> topITEmployees = employees.stream()
                .filter(emp -> "IT".equals(emp.getDepartment()))           // Filter IT employees
                .filter(emp -> emp.getHireYear() > 2020)                   // Filter recent hires
                .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary())) // Sort by salary desc
                .limit(3)                                                  // Take top 3
                .toList();

        System.out.println("Top 3 IT employees hired after 2020:");
        topITEmployees.forEach(System.out::println);

        // Group employees by department and calculate average salary
        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));

        System.out.println("\nAverage salary by department:");
        avgSalaryByDept.forEach((dept, avg) ->
                System.out.printf("%s: $%.2f%n", dept, avg));

        // Find department with highest total salary
        Optional<Map.Entry<String, Double>> highestPayingDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.summingDouble(Employee::getSalary)
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        highestPayingDept.ifPresent(entry ->
                System.out.printf("\nHighest paying department: %s ($%.2f)%n",
                        entry.getKey(), entry.getValue()));

        System.out.println();
    }

    /**
     * Helper method to create sample employee data
     */
    private static List<Employee> createEmployeeData() {
        return Arrays.asList(
                new Employee("Alice Johnson", "IT", 75000, 2019),
                new Employee("Bob Smith", "IT", 82000, 2021),
                new Employee("Charlie Brown", "HR", 65000, 2020),
                new Employee("Diana Wilson", "IT", 95000, 2022),
                new Employee("Eve Davis", "Finance", 70000, 2018),
                new Employee("Frank Miller", "HR", 68000, 2021),
                new Employee("Grace Lee", "IT", 88000, 2023),
                new Employee("Henry Taylor", "Finance", 72000, 2019)
        );
    }

    /**
     * Helper method to perform mathematical operations using functional interfaces
     */
    private static int performOperation(int a, int b, MathOperation operation) {
        return operation.operate(a, b);
    }

    /**
     * Custom functional interface for mathematical operations
     */
    @FunctionalInterface
    interface MathOperation {
        int operate(int a, int b);
    }

    /**
     * Simple Employee class for demonstration
     */
    static class Employee {

        private final String name;
        private final String department;
        private final double salary;
        private final int hireYear;

        public Employee(String name, String department, double salary, int hireYear) {
            this.name = name;
            this.department = department;
            this.salary = salary;
            this.hireYear = hireYear;
        }

        // Getters
        public String getName() { return name; }
        public String getDepartment() { return department; }
        public double getSalary() { return salary; }
        public int getHireYear() { return hireYear; }

        @Override
        public String toString() {
            return String.format("%s (%s) - $%.2f (hired %d)",
                    name, department, salary, hireYear);
        }
    }

}
