package com.vladproduction.collectionsframework.ch08_streams;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

/**
 * CollectionStreams - Advanced Stream Operations with Collections
 *
 * This class demonstrates advanced Stream API operations specifically focused on
 * working with collections, including grouping, partitioning, and collecting
 * operations that are essential for real-world data processing.
 *
 * Key Concepts Covered:
 * - Collectors class and its utility methods
 * - Grouping operations (groupingBy, partitioningBy)
 * - Downstream collectors and multi-level grouping
 * - Custom collectors
 * - Stream operations on different collection types
 * - Converting between collection types using streams
 *
 * Learning Objectives:
 * - Master the Collectors utility class
 * - Understand grouping and partitioning operations
 * - Learn to create custom collectors
 * - Practice advanced collection transformations
 * - Implement real-world data processing scenarios
 *
 * @author vladproduction
 * @version 1.0
 * @since Java 8
 */
public class CollectionStreams {
    /**
     * Main method demonstrating advanced collection stream operations
     */
    public static void main(String[] args) {
        System.out.println("=== Advanced Collection Stream Operations ===".toUpperCase());
        System.out.println("=".repeat(50));
        System.out.println();

        // Basic collection operations
        demonstrateBasicCollectionOperations();

        // Grouping operations
        demonstrateGroupingOperations();

        // Partitioning operations
        demonstratePartitioningOperations();

        // Downstream collectors
        demonstrateDownstreamCollectors();

        // Multi-level grouping
        demonstrateMultiLevelGrouping();

        // Custom collectors
        demonstrateCustomCollectors();

        // Collection type conversions
        demonstrateCollectionConversions();

        // Real-world examples
        demonstrateRealWorldExamples();
    }

    /**
     * Demonstrates basic collection operations with streams
     *
     * Basic Collection Operations:
     * - toList(): Collect to ArrayList
     * - toSet(): Collect to HashSet
     * - toMap(): Collect to HashMap
     * - joining(): Join strings
     * - counting(): Count elements
     * - summingInt/Double(): Sum numeric values
     * - averagingInt/Double(): Calculate averages
     */
    private static void demonstrateBasicCollectionOperations() {
        System.out.println("1. Basic Collection Operations:");

        List<Product> products = createProductData();

        // toList() - Default ArrayList
        List<String> productNames = products.stream()
                .map(Product::getName)
                .toList();
        System.out.println("Product names (List): " + productNames);

        // toSet() - Remove duplicates
        Set<String> categories = products.stream()
                .map(Product::getCategory)
                .collect(Collectors.toSet());
        System.out.println("Unique categories (Set): " + categories);

        // toMap() - Create name to price mapping
        Map<String, Double> nameToPrice = products.stream()
                .collect(Collectors.toMap(
                        Product::getName,
                        Product::getPrice
                ));
        System.out.println("Name to price mapping (Map):");
        nameToPrice.forEach((name, price) -> System.out.printf("  %s: $%.2f%n", name, price));

        // joining() - Join product names
        String allNames = products.stream()
                .map(Product::getName)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("All names joined: " + allNames);

        // Statistical operations
        long totalProducts = products.stream().collect(Collectors.counting());
        double totalValue = products.stream().collect(Collectors.summingDouble(Product::getPrice));
        double averagePrice = products.stream().collect(Collectors.averagingDouble(Product::getPrice));

        System.out.printf("Total products: %d%n", totalProducts);
        System.out.printf("Total value: $%.2f%n", totalValue);
        System.out.printf("Average price: $%.2f%n", averagePrice);

        System.out.println();
    }

    /**
     * Demonstrates grouping operations
     *
     * Grouping Operations:
     * - groupingBy(): Group elements by a classifier function
     * - Returns Map<K, List<T>> by default
     * - Can be combined with downstream collectors for different result types
     * - Supports multi-level grouping with nested maps
     */
    private static void demonstrateGroupingOperations() {
        System.out.println("2. Grouping Operations:");

        List<Product> products = createProductData();

        // Basic grouping by category
        Map<String, List<Product>> productsByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        System.out.println("Products grouped by category:");
        productsByCategory.forEach((category, productList) -> {
            System.out.println("  " + category + ":");
            productList.forEach(p -> System.out.println("    " + p));
        });

        // Group by price range
        Map<String, List<Product>> productsByPriceRange = products.stream()
                .collect(Collectors.groupingBy(product -> {
                    double price = product.getPrice();
                    if (price < 100) return "Budget";
                    else if (price < 500) return "Mid-range";
                    else return "Premium";
                }));

        System.out.println("\nProducts grouped by price range:");
        productsByPriceRange.forEach((range, productList) -> {
            System.out.println("  " + range + " (" + productList.size() + " products):");
            productList.forEach(p -> System.out.println("    " + p.getName() + " - $" + p.getPrice()));
        });

        // Group by multiple conditions using custom classifier
        Map<String, List<Product>> complexGrouping = products.stream()
                .collect(Collectors.groupingBy(product ->
                        product.getCategory() + "_" + (product.getPrice() > 200 ? "Expensive" : "Affordable")
                ));

        System.out.println("\nComplex grouping (Category_PriceLevel):");
        complexGrouping.forEach((key, productList) ->
                System.out.println("  " + key + ": " + productList.size() + " products"));

        System.out.println();
    }

    /**
     * Demonstrates partitioning operations
     *
     * Partitioning Operations:
     * - partitioningBy(): Split collection into two groups based on a predicate
     * - Returns Map<Boolean, List<T>>
     * - True key contains elements matching the predicate
     * - False key contains elements not matching the predicate
     * - More efficient than groupingBy for binary classification
     */
    private static void demonstratePartitioningOperations() {
        System.out.println("3. Partitioning Operations:");

        List<Product> products = createProductData();

        // Partition by price threshold
        Map<Boolean, List<Product>> partitionedByPrice = products.stream()
                .collect(Collectors.partitioningBy(product -> product.getPrice() > 200));

        System.out.println("Products partitioned by price > $200:");
        System.out.println("  Expensive (>$200): " + partitionedByPrice.get(true).size() + " products");
        partitionedByPrice.get(true).forEach(p ->
                System.out.println("    " + p.getName() + " - $" + p.getPrice()));

        System.out.println("  Affordable (≤$200): " + partitionedByPrice.get(false).size() + " products");
        partitionedByPrice.get(false).forEach(p ->
                System.out.println("    " + p.getName() + " - $" + p.getPrice()));

        // Partition by category
        Map<Boolean, List<Product>> partitionedByElectronics = products.stream()
                .collect(Collectors.partitioningBy(product -> "Electronics".equals(product.getCategory())));

        System.out.println("\nProducts partitioned by Electronics category:");
        System.out.println("  Electronics: " + partitionedByElectronics.get(true).size() + " products");
        System.out.println("  Other categories: " + partitionedByElectronics.get(false).size() + " products");

        // Partition with complex predicate
        Predicate<Product> isPremiumElectronics = product ->
                "Electronics".equals(product.getCategory()) && product.getPrice() > 500;

        Map<Boolean, List<Product>> premiumElectronics = products.stream()
                .collect(Collectors.partitioningBy(isPremiumElectronics));

        System.out.println("\nPremium Electronics partition:");
        System.out.println("  Premium Electronics: " + premiumElectronics.get(true).size() + " products");
        System.out.println("  Others: " + premiumElectronics.get(false).size() + " products");

        System.out.println();
    }

    /**
     * Demonstrates downstream collectors
     *
     * Downstream Collectors:
     * - Used as second argument to groupingBy() and partitioningBy()
     * - Transform the grouped/partitioned values
     * - Common downstream collectors: counting(), summingInt(), averagingDouble(),
     *   mapping(), filtering(), reducing(), collectingAndThen()
     */
    private static void demonstrateDownstreamCollectors() {
        System.out.println("4. Downstream Collectors:");

        List<Product> products = createProductData();

        // Count products by category
        Map<String, Long> countByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));

        System.out.println("Product count by category:");
        countByCategory.forEach((category, count) ->
                System.out.println("  " + category + ": " + count + " products"));

        // Sum prices by category
        Map<String, Double> totalValueByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.summingDouble(Product::getPrice)));

        System.out.println("\nTotal value by category:");
        totalValueByCategory.forEach((category, total) ->
                System.out.printf("  %s: $%.2f%n", category, total));

        // Average price by category
        Map<String, Double> avgPriceByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.averagingDouble(Product::getPrice)));

        System.out.println("\nAverage price by category:");
        avgPriceByCategory.forEach((category, avg) ->
                System.out.printf("  %s: $%.2f%n", category, avg));

        // Get product names by category using mapping()
        Map<String, List<String>> namesByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.mapping(Product::getName, Collectors.toList())));

        System.out.println("\nProduct names by category:");
        namesByCategory.forEach((category, names) ->
                System.out.println("  " + category + ": " + names));

        // Get expensive products by category using filtering()
        Map<String, List<Product>> expensiveByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.filtering(p -> p.getPrice() > 200, Collectors.toList())));

        System.out.println("\nExpensive products (>$200) by category:");
        expensiveByCategory.forEach((category, expensiveProducts) -> {
            if (!expensiveProducts.isEmpty()) {
                System.out.println("  " + category + ":");
                expensiveProducts.forEach(p -> System.out.println("    " + p.getName() + " - $" + p.getPrice()));
            }
        });

        // Using collectingAndThen() to post-process results
        Map<String, String> summaryByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.size() + " products, avg price: $" +
                                        String.format("%.2f", list.stream().mapToDouble(Product::getPrice).average().orElse(0))
                        )));

        System.out.println("\nCategory summaries:");
        summaryByCategory.forEach((category, summary) ->
                System.out.println("  " + category + ": " + summary));

        System.out.println();
    }

    /**
     * Demonstrates multi-level grouping
     *
     * Multi-level Grouping:
     * - Nested grouping operations create maps of maps
     * - First level groups by primary classifier
     * - Second level groups by secondary classifier within each primary group
     * - Can be extended to multiple levels
     */
    private static void demonstrateMultiLevelGrouping() {
        System.out.println("5. Multi-Level Grouping:");

        List<Employee> employees = createEmployeeData();

        // Two-level grouping: Department -> Salary Range
        Map<String, Map<String, List<Employee>>> deptSalaryGrouping = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.groupingBy(emp -> {
                            double salary = emp.getSalary();
                            if (salary < 70000) return "Junior";
                            else if (salary < 85000) return "Mid";
                            else return "Senior";
                        })));

        System.out.println("Employees grouped by Department -> Salary Level:");
        deptSalaryGrouping.forEach((dept, salaryGroups) -> {
            System.out.println("  " + dept + ":");
            salaryGroups.forEach((level, empList) -> {
                System.out.println("    " + level + " (" + empList.size() + " employees):");
                empList.forEach(emp -> System.out.println("      " + emp.getName() + " - $" + emp.getSalary()));
            });
        });

        // Three-level grouping: Department -> Hire Year -> Salary Range
        Map<String, Map<Integer, Map<String, List<Employee>>>> complexGrouping = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.groupingBy(Employee::getHireYear,
                                Collectors.groupingBy(emp -> emp.getSalary() > 75000 ? "High" : "Standard"))));

        System.out.println("\nThree-level grouping (Department -> Year -> Salary):");
        complexGrouping.forEach((dept, yearGroups) -> {
            System.out.println("  " + dept + ":");
            yearGroups.forEach((year, salaryGroups) -> {
                System.out.println("    " + year + ":");
                salaryGroups.forEach((salaryLevel, empList) ->
                        System.out.println("      " + salaryLevel + ": " + empList.size() + " employees"));
            });
        });

        // Multi-level with downstream collectors
        Map<String, Map<String, Double>> deptSalaryAverage = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.groupingBy(emp -> emp.getSalary() > 75000 ? "High" : "Standard",
                                Collectors.averagingDouble(Employee::getSalary))));

        System.out.println("\nAverage salary by Department -> Salary Level:");
        deptSalaryAverage.forEach((dept, salaryAvgs) -> {
            System.out.println("  " + dept + ":");
            salaryAvgs.forEach((level, avg) ->
                    System.out.printf("    %s: $%.2f%n", level, avg));
        });

        System.out.println();
    }

    /**
     * Demonstrates custom collectors
     *
     * Custom Collectors:
     * - Created using Collector.of() method
     * - Requires supplier, accumulator, combiner, and optionally finisher
     * - Useful for complex aggregation operations
     * - Can be reused across different streams
     */
    private static void demonstrateCustomCollectors() {
        System.out.println("6. Custom Collectors:");

        List<Product> products = createProductData();

        // Custom collector to create product summary
        Collector<Product, ?, String> productSummaryCollector = Collector.of(
                // Supplier: create initial container
                () -> new StringBuilder(),
                // Accumulator: add elements to container
                (sb, product) -> sb.append(product.getName()).append(" ($").append(product.getPrice()).append("), "),
                // Combiner: combine two containers (for parallel processing)
                (sb1, sb2) -> sb1.append(sb2),
                // Finisher: final transformation
                sb -> sb.length() > 2 ? sb.substring(0, sb.length() - 2) : sb.toString()
        );

        String productSummary = products.stream()
                .filter(p -> p.getPrice() > 100)
                .collect(productSummaryCollector);

        System.out.println("Product summary (>$100): " + productSummary);

        // Custom collector for statistics
        Collector<Product, ?, ProductStatistics> statisticsCollector = Collector.of(
                ProductStatistics::new,
                ProductStatistics::addProduct,
                ProductStatistics::combine
        );

        ProductStatistics stats = products.stream().collect(statisticsCollector);
        System.out.println("\nProduct Statistics:");
        System.out.println("  Count: " + stats.getCount());
        System.out.printf("  Total Value: $%.2f%n", stats.getTotalValue());
        System.out.printf("  Average Price: $%.2f%n", stats.getAveragePrice());
        System.out.printf("  Min Price: $%.2f%n", stats.getMinPrice());
        System.out.printf("  Max Price: $%.2f%n", stats.getMaxPrice());

        // Custom collector to find top N products by price
        Collector<Product, ?, List<Product>> topNCollector = topN(3);

        List<Product> top3Products = products.stream().collect(topNCollector);
        System.out.println("\nTop 3 most expensive products:");
        top3Products.forEach(p -> System.out.println("  " + p.getName() + " - $" + p.getPrice()));

        System.out.println();
    }

    /**
     * Demonstrates collection type conversions using streams
     *
     * Collection Conversions:
     * - Convert between different collection types (List, Set, Map)
     * - Transform collection structures
     * - Create immutable collections
     * - Convert to specialized collections (TreeSet, LinkedHashMap, etc.)
     */
    private static void demonstrateCollectionConversions() {
        System.out.println("7. Collection Type Conversions:");

        List<Product> products = createProductData();

        // List to Set (remove duplicates)
        Set<String> uniqueCategories = products.stream()
                .map(Product::getCategory)
                .collect(Collectors.toSet());
        System.out.println("Unique categories (Set): " + uniqueCategories);

        // List to TreeSet (sorted set)
        TreeSet<String> sortedCategories = products.stream()
                .map(Product::getCategory)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("Sorted categories (TreeSet): " + sortedCategories);

        // List to LinkedHashMap (preserve insertion order)
        LinkedHashMap<String, Double> orderedNamePrice = products.stream()
                .collect(Collectors.toMap(
                        Product::getName,
                        Product::getPrice,
                        (existing, replacement) -> existing,  // Handle duplicates
                        LinkedHashMap::new
                ));
        System.out.println("Ordered name-price mapping:");
        orderedNamePrice.forEach((name, price) -> System.out.printf("  %s: $%.2f%n", name, price));

        // Array to Stream to Collection
        String[] categoryArray = {"Electronics", "Clothing", "Books", "Electronics"};
        List<String> categoryList = Arrays.stream(categoryArray)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Array to distinct sorted list: " + categoryList);

        // Map to different Map type with transformation
        Map<String, List<String>> categoryToNames = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.mapping(Product::getName, Collectors.toList())
                ));

        // Convert to Map<String, String> with joined names
        Map<String, String> categoryToJoinedNames = categoryToNames.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> String.join(", ", entry.getValue())
                ));

        System.out.println("\nCategory to joined names:");
        categoryToJoinedNames.forEach((category, names) ->
                System.out.println("  " + category + ": " + names));

        // Create immutable collections (using Collectors.toUnmodifiableList(), etc.)
        List<String> immutableNames = products.stream()
                .map(Product::getName)
                .collect(Collectors.toUnmodifiableList());
        System.out.println("\nImmutable product names: " + immutableNames);

        System.out.println();
    }

    /**
     * Demonstrates real-world examples of collection stream operations
     *
     * Real-world scenarios:
     * - E-commerce data analysis
     * - Sales reporting
     * - Inventory management
     * - Customer segmentation
     */
    private static void demonstrateRealWorldExamples() {
        System.out.println("8. Real-World Examples:");

        List<Order> orders = createOrderData();

        // 1. Sales report by month
        Map<String, Double> salesByMonth = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> order.getDate().substring(0, 7), // Extract YYYY-MM
                        Collectors.summingDouble(Order::getAmount)
                ));

        System.out.println("Monthly sales report:");
        salesByMonth.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.printf("  %s: $%.2f%n", entry.getKey(), entry.getValue()));

        // 2. Top customers by total spending
        Map<String, Double> customerSpending = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomerId,
                        Collectors.summingDouble(Order::getAmount)
                ));

        List<Map.Entry<String, Double>> topCustomers = customerSpending.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("\nTop 3 customers by spending:");
        topCustomers.forEach(entry ->
                System.out.printf("  Customer %s: $%.2f%n", entry.getKey(), entry.getValue()));

        // 3. Order status distribution
        Map<String, Long> statusDistribution = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus, Collectors.counting()));

        System.out.println("\nOrder status distribution:");
        statusDistribution.forEach((status, count) ->
                System.out.println("  " + status + ": " + count + " orders"));

        // 4. Average order value by customer segment
        Map<String, Double> avgOrderBySegment = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> order.getAmount() > 500 ? "Premium" : "Regular",
                        Collectors.averagingDouble(Order::getAmount)
                ));

        System.out.println("\nAverage order value by segment:");
        avgOrderBySegment.forEach((segment, avg) ->
                System.out.printf("  %s: $%.2f%n", segment, avg));

        // 5. Complex business analysis: Find customers with multiple orders over $100
        Set<String> frequentHighValueCustomers = orders.stream()
                .filter(order -> order.getAmount() > 100)
                .collect(Collectors.groupingBy(Order::getCustomerId, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        System.out.println("\nCustomers with multiple orders >$100: " + frequentHighValueCustomers);

        System.out.println();
    }

    /**
     * Helper method to create sample product data
     */
    private static List<Product> createProductData() {
        return Arrays.asList(
                new Product("Laptop", "Electronics", 999.99),
                new Product("Smartphone", "Electronics", 699.99),
                new Product("Headphones", "Electronics", 149.99),
                new Product("T-Shirt", "Clothing", 29.99),
                new Product("Jeans", "Clothing", 79.99),
                new Product("Sneakers", "Clothing", 119.99),
                new Product("Java Book", "Books", 49.99),
                new Product("Python Guide", "Books", 39.99),
                new Product("Tablet", "Electronics", 399.99),
                new Product("Jacket", "Clothing", 199.99)
        );
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
     * Helper method to create sample order data
     */
    private static List<Order> createOrderData() {
        return Arrays.asList(
                new Order("O001", "C001", 250.00, "2024-01-15", "Completed"),
                new Order("O002", "C002", 150.75, "2024-01-20", "Shipped"),
                new Order("O003", "C001", 320.50, "2024-02-05", "Completed"),
                new Order("O004", "C003", 89.99, "2024-02-10", "Processing"),
                new Order("O005", "C002", 450.00, "2024-02-15", "Completed"),
                new Order("O006", "C004", 125.25, "2024-03-01", "Shipped"),
                new Order("O007", "C001", 75.50, "2024-03-05", "Cancelled"),
                new Order("O008", "C003", 600.00, "2024-03-10", "Completed"),
                new Order("O009", "C005", 200.00, "2024-03-15", "Processing"),
                new Order("O010", "C002", 350.75, "2024-03-20", "Shipped")
        );
    }

    /**
     * Helper method to create a custom collector for top N elements
     */
    private static <T> Collector<T, ?, List<T>> topN(int n, Comparator<? super T> comparator) {
        return Collector.of(
                () -> new PriorityQueue<T>(n, comparator),
                (queue, item) -> {
                    if (queue.size() < n) {
                        queue.offer(item);
                    } else if (comparator.compare(item, queue.peek()) > 0) {
                        queue.poll();
                        queue.offer(item);
                    }
                },
                (q1, q2) -> {
                    q1.addAll(q2);
                    return q1.stream()
                            .sorted(comparator.reversed())
                            .limit(n)
                            .collect(Collectors.toCollection(() -> new PriorityQueue<>(comparator.reversed())));
                },
                queue -> queue.stream()
                        .sorted(comparator.reversed())
                        .collect(Collectors.toList())
        );
    }

    /**
     * Custom collector for top N products by price
     */
    private static Collector<Product, ?, List<Product>> topN(int n) {
        return Collector.of(
                () -> new PriorityQueue<Product>(n, Comparator.comparing(Product::getPrice)),
                (queue, product) -> {
                    if (queue.size() < n) {
                        queue.offer(product);
                    } else if (product.getPrice() > queue.peek().getPrice()) {
                        queue.poll();
                        queue.offer(product);
                    }
                },
                (queue1, queue2) -> {
                    queue1.addAll(queue2);
                    return queue1.stream()
                            .sorted(Comparator.comparing(Product::getPrice).reversed())
                            .limit(n)
                            .collect(Collectors.toCollection(() ->
                                    new PriorityQueue<>(Comparator.comparing(Product::getPrice))));
                },
                queue -> queue.stream()
                        .sorted(Comparator.comparing(Product::getPrice).reversed())
                        .collect(Collectors.toList())
        );
    }

    /**
     * Product class for demonstration
     */
    static class Product implements Comparable<Product> {

        private final String name;
        private final String category;
        private final double price;

        public Product(String name, String category, double price) {
            this.name = name;
            this.category = category;
            this.price = price;
        }

        // Getters
        public String getName() { return name; }
        public String getCategory() { return category; }
        public double getPrice() { return price; }

        @Override
        public int compareTo(Product other) {
            return Double.compare(this.price, other.price);
        }

        @Override
        public String toString() {
            return String.format("%s (%s) - $%.2f", name, category, price);
        }
    }

    /**
     * Employee class for demonstration
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
    }

    /**
     * Order class for demonstration
     */
    static class Order {

        private final String orderId;
        private final String customerId;
        private final double amount;
        private final String date;
        private final String status;

        public Order(String orderId, String customerId, double amount, String date, String status) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.amount = amount;
            this.date = date;
            this.status = status;
        }

        // Getters
        public String getOrderId() { return orderId; }
        public String getCustomerId() { return customerId; }
        public double getAmount() { return amount; }
        public String getDate() { return date; }
        public String getStatus() { return status; }
    }

    /**
     * Custom statistics class for collecting product statistics
     */
    static class ProductStatistics {
        private int count = 0;
        private double totalValue = 0.0;
        private double minPrice = Double.MAX_VALUE;
        private double maxPrice = Double.MIN_VALUE;

        public void addProduct(Product product) {
            count++;
            totalValue += product.getPrice();
            minPrice = Math.min(minPrice, product.getPrice());
            maxPrice = Math.max(maxPrice, product.getPrice());
        }

        public ProductStatistics combine(ProductStatistics other) {
            ProductStatistics combined = new ProductStatistics();
            combined.count = this.count + other.count;
            combined.totalValue = this.totalValue + other.totalValue;
            combined.minPrice = Math.min(this.minPrice, other.minPrice);
            combined.maxPrice = Math.max(this.maxPrice, other.maxPrice);
            return combined;
        }

        // Getters
        public int getCount() { return count; }
        public double getTotalValue() { return totalValue; }
        public double getAveragePrice() { return count > 0 ? totalValue / count : 0.0; }
        public double getMinPrice() { return count > 0 ? minPrice : 0.0; }
        public double getMaxPrice() { return count > 0 ? maxPrice : 0.0; }
    }

}
