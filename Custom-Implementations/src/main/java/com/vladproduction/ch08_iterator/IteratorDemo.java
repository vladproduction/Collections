package com.vladproduction.ch08_iterator;


import com.vladproduction.ch08_iterator.binary_tree_traversal_iterators.BinaryTree;
import com.vladproduction.ch08_iterator.binary_tree_traversal_iterators.TreeNode;
import com.vladproduction.ch08_iterator.circular_and_infinities_iterators.CircularIterator;
import com.vladproduction.ch08_iterator.matrix_iterators.Matrix;

import java.util.*;


/**
 * Comprehensive demonstration of custom iterator implementations
 */
public class IteratorDemo {
    
    public static void main(String[] args) {

        System.out.println("Custom Iterator Implementations Demo");
        System.out.println("===================================");
        
        demonstrateBinaryTreeTraversal();
        demonstrateMatrixTraversal();
        demonstrateFilteringAndTransforming();
        demonstrateAdvancedIterators();
        demonstrateRealWorldScenarios();

        AdvancedIteratorDemo.runAdvancedExamples();
    }
    
    private static void demonstrateBinaryTreeTraversal() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("BINARY TREE TRAVERSAL ITERATORS");
        System.out.println("=".repeat(60));
        
        // Create a sample binary tree:
        //       1
        //      / \
        //     2   3
        //    / \   \
        //   4   5   6
        TreeNode<Integer> root = new TreeNode<>(1,
            new TreeNode<>(2,
                new TreeNode<>(4),
                new TreeNode<>(5)
            ),
            new TreeNode<>(3,
                null,
                new TreeNode<>(6)
            )
        );
        
        BinaryTree<Integer> tree = new BinaryTree<>(root);
        
        // Demonstrate different traversal orders
        IteratorUtils.printAll(tree.getIterator(BinaryTree.TraversalType.IN_ORDER), 
                              "In-Order Traversal (4 2 5 1 3 6)");
        IteratorUtils.printAll(tree.getIterator(BinaryTree.TraversalType.PRE_ORDER), 
                              "Pre-Order Traversal (1 2 4 5 3 6)");
        IteratorUtils.printAll(tree.getIterator(BinaryTree.TraversalType.POST_ORDER), 
                              "Post-Order Traversal (4 5 2 6 3 1)");
        IteratorUtils.printAll(tree.getIterator(BinaryTree.TraversalType.LEVEL_ORDER), 
                              "Level-Order Traversal (1 2 3 4 5 6)");
        
        // Demonstrate peek functionality
        System.out.println("\nPeek functionality (BinaryTree.TraversalType.IN_ORDER):");
        EnhancedIterator<Integer> inOrderIter = (EnhancedIterator<Integer>) 
            tree.getIterator(BinaryTree.TraversalType.IN_ORDER);
        System.out.println("First element (peek): " + inOrderIter.peek());
        System.out.println("First element (next): " + inOrderIter.next());
        System.out.println("Second element (peek): " + inOrderIter.peek());
    }
    
    private static void demonstrateMatrixTraversal() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("MATRIX TRAVERSAL ITERATORS");
        System.out.println("=".repeat(60));
        
        // Create a 3x4 matrix
        Matrix<Integer> matrix = new Matrix<>(3, 4);
        int value = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                matrix.set(i, j, value++);
            }
        }
        
        System.out.println("Matrix (3x4):");
        System.out.println("1  2  3  4");
        System.out.println("5  6  7  8");
        System.out.println("9  10 11 12");
        
        // Demonstrate different traversal patterns
        IteratorUtils.printAll(matrix.getIterator(Matrix.TraversalPattern.ROW_MAJOR), 
                              "Row-Major Order");
        IteratorUtils.printAll(matrix.getIterator(Matrix.TraversalPattern.COLUMN_MAJOR), 
                              "Column-Major Order");
        IteratorUtils.printAll(matrix.getIterator(Matrix.TraversalPattern.DIAGONAL), 
                              "Diagonal Traversal");
        IteratorUtils.printAll(matrix.getIterator(Matrix.TraversalPattern.SPIRAL), 
                              "Spiral Traversal");
        IteratorUtils.printAll(matrix.getIterator(Matrix.TraversalPattern.ZIGZAG), 
                              "ZigZag Traversal");
    }
    
    private static void demonstrateFilteringAndTransforming() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("FILTERING AND TRANSFORMING ITERATORS");
        System.out.println("=".repeat(60));
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Filtering: only even numbers
        Iterator<Integer> evenNumbers = IteratorUtils.filter(
            numbers.iterator(), 
            n -> n % 2 == 0
        );
        IteratorUtils.printAll(evenNumbers, "Even numbers only");
        
        // Transforming: square all numbers
        Iterator<Integer> squaredNumbers = IteratorUtils.map(
            numbers.iterator(), 
            n -> n * n
        );
        IteratorUtils.printAll(squaredNumbers, "Squared numbers");
        
        // Chaining: filter evens, then square, then limit to 3
        Iterator<Integer> complexChain = IteratorUtils.limit(
            IteratorUtils.map(
                IteratorUtils.filter(numbers.iterator(), n -> n % 2 == 0),
                n -> n * n
            ),
            3
        );
        IteratorUtils.printAll(complexChain, "Even numbers squared, limited to 3");
    }
    
    private static void demonstrateAdvancedIterators() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ADVANCED ITERATORS");
        System.out.println("=".repeat(60));
        
        // Circular Iterator
        List<String> colors = Arrays.asList("Red", "Green", "Blue");
        CircularIterator<String> circularIter = IteratorUtils.cycle(colors);
        
        System.out.println("Circular Iterator (10 elements):");
        for (int i = 0; i < 10; i++) {
            System.out.print(circularIter.next() + " ");
            if ((i + 1) % 3 == 0) {
                System.out.print("| "); // Mark cycle boundaries
            }
        }
        System.out.println("\nCycles completed: " + circularIter.getCycleCount());
        
        // Chained Iterator
        List<String> list1 = Arrays.asList("A", "B", "C");
        List<String> list2 = Arrays.asList("D", "E");
        List<String> list3 = Arrays.asList("F", "G", "H", "I");
        
        Iterator<String> chainedIter = IteratorUtils.chain(
            list1.iterator(), list2.iterator(), list3.iterator()
        );
        IteratorUtils.printAll(chainedIter, "Chained iterators");
        
        // Limited Iterator with circular base
        circularIter.reset();
        Iterator<String> limitedCircular = IteratorUtils.limit(circularIter, 7);
        IteratorUtils.printAll(limitedCircular, "Limited circular iterator (7 elements)");
    }
    
    private static void demonstrateRealWorldScenarios() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("REAL-WORLD SCENARIOS");
        System.out.println("=".repeat(60));
        
        // Scenario 1: Processing log files with filtering
        demonstrateLogProcessing();
        
        // Scenario 2: Paginated data processing
        demonstratePaginatedDataProcessing();
        
        // Scenario 3: File system traversal
        demonstrateFileSystemTraversal();
    }
    
    private static void demonstrateLogProcessing() {
        System.out.println("\n--- Log Processing Scenario ---");
        
        // Simulate log entries
        List<String> logEntries = Arrays.asList(
            "INFO: Application started",
            "DEBUG: Loading configuration",
            "ERROR: Database connection failed",
            "INFO: Retrying database connection",
            "WARN: High memory usage detected",
            "ERROR: Authentication failed for user123",
            "INFO: User logged in successfully",
            "DEBUG: Processing request #1001"
        );
        
        // Filter only ERROR and WARN entries, then transform to uppercase
        Iterator<String> criticalLogs = IteratorUtils.map(
            IteratorUtils.filter(
                logEntries.iterator(),
                log -> log.contains("ERROR") || log.contains("WARN")
            ),
            String::toUpperCase
        );
        
        IteratorUtils.printAll(criticalLogs, "Critical log entries (ERROR/WARN only)");
    }
    
    private static void demonstratePaginatedDataProcessing() {
        System.out.println("\n--- Paginated Data Processing Scenario ---");
        
        // Simulate paginated API responses
        class PaginatedIterator<T> implements Iterator<T> {
            private final List<List<T>> pages;
            private int currentPage = 0;
            private int currentIndex = 0;
            
            public PaginatedIterator(List<List<T>> pages) {
                this.pages = pages;
            }
            
            @Override
            public boolean hasNext() {
                while (currentPage < pages.size()) {
                    if (currentIndex < pages.get(currentPage).size()) {
                        return true;
                    }
                    currentPage++;
                    currentIndex = 0;
                }
                return false;
            }
            
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return pages.get(currentPage).get(currentIndex++);
            }
        }
        
        // Create mock paginated data
        List<List<String>> pages = Arrays.asList(
            Arrays.asList("User1", "User2", "User3"),      // Page 1
            Arrays.asList("User4", "User5"),               // Page 2
            Arrays.asList("User6", "User7", "User8", "User9"), // Page 3
            Arrays.asList()                                // Empty page 4
        );
        
        PaginatedIterator<String> paginatedIter = new PaginatedIterator<>(pages);
        IteratorUtils.printAll(paginatedIter, "Paginated user data (seamless iteration)");
    }
    
    private static void demonstrateFileSystemTraversal() {
        System.out.println("\n--- File System Traversal Scenario ---");
        
        // Simulate a directory structure
        class FileNode {
            String name;
            boolean isDirectory;
            List<FileNode> children;
            
            FileNode(String name, boolean isDirectory) {
                this.name = name;
                this.isDirectory = isDirectory;
                this.children = new ArrayList<>();
            }
            
            void addChild(FileNode child) {
                children.add(child);
            }
            
            @Override
            public String toString() {
                return name + (isDirectory ? "/" : "");
            }
        }
        
        // Directory traversal iterator
        class DirectoryIterator implements Iterator<FileNode> {
            private final Stack<Iterator<FileNode>> stack = new Stack<>();
            private final boolean includeDirectories;
            
            DirectoryIterator(FileNode root, boolean includeDirectories) {
                this.includeDirectories = includeDirectories;
                if (root != null) {
                    stack.push(Arrays.asList(root).iterator());
                }
            }
            
            @Override
            public boolean hasNext() {
                while (!stack.isEmpty()) {
                    if (stack.peek().hasNext()) {
                        return true;
                    }
                    stack.pop();
                }
                return false;
            }
            
            @Override
            public FileNode next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                
                FileNode current = stack.peek().next();
                
                // Add children to stack for traversal
                if (current.isDirectory && !current.children.isEmpty()) {
                    stack.push(current.children.iterator());
                }
                
                // If we don't want directories, skip them
                if (!includeDirectories && current.isDirectory) {
                    return hasNext() ? next() : null;
                }
                
                return current;
            }
        }
        
        // Build sample directory structure
        FileNode root = new FileNode("project", true);
        FileNode src = new FileNode("src", true);
        FileNode main = new FileNode("main", true);
        FileNode java = new FileNode("java", true);
        
        java.addChild(new FileNode("Main.java", false));
        java.addChild(new FileNode("Utils.java", false));
        main.addChild(java);
        main.addChild(new FileNode("resources", true));
        src.addChild(main);
        
        FileNode test = new FileNode("test", true);
        test.addChild(new FileNode("TestMain.java", false));
        src.addChild(test);
        
        root.addChild(src);
        root.addChild(new FileNode("README.md", false));
        root.addChild(new FileNode("pom.xml", false));
        
        // Traverse and show all files (no directories)
        DirectoryIterator fileIter = new DirectoryIterator(root, false);
        System.out.println("Files only:");
        while (fileIter.hasNext()) {
            FileNode file = fileIter.next();
            if (file != null) {
                System.out.println("  " + file);
            }
        }
        
        // Traverse and show everything
        DirectoryIterator allIter = new DirectoryIterator(root, true);
        System.out.println("\nAll items (files and directories):");
        while (allIter.hasNext()) {
            FileNode item = allIter.next();
            if (item != null) {
                System.out.println("  " + item);
            }
        }
    }
}