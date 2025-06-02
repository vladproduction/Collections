package com.vladproduction.ch04_stack.arraylist;

/**
 * this main is created just for demonstration purposes and show how ArrayList resize internally
 * while we push new elements to provoke resizing in case capacity is shorter than size
 * */
public class StackResizingDemo {

    public static void main(String[] args) {
        MyStackArrayListImpl<Integer> stack = new MyStackArrayListImpl<>();

        System.out.println("=== Stack Dynamic Resizing Demonstration ===\n");

        // Demonstrate adding elements and automatic resizing
        System.out.println("Adding elements to stack (automatic resizing happens internally):");

        for (int i = 1; i <= 15; i++) {
            stack.push(i);
            System.out.printf("Pushed %2d -> Size: %2d | %s%n",
                    i, stack.size(), stack.toString());

            // Show when potential resizing might occur (ArrayList default initial capacity is 10)
            if (i == 10) {
                System.out.println("    ↑ Internal ArrayList likely resized here (capacity exceeded)");
            }
        }

        System.out.println("\n=== Testing Stack Operations After Resizing ===");

        // Demonstrate that all operations work normally after resizing
        System.out.println("Current stack: " + stack);
        System.out.println("Stack size: " + stack.size());
        System.out.println("Top element (peek): " + stack.peek());
        System.out.println("Contains 5: " + stack.contains(5));
        System.out.println("Contains 20: " + stack.contains(20));

        System.out.println("\nPopping some elements:");
        for (int i = 0; i < 5; i++) {
            Integer popped = stack.pop();
            System.out.printf("Popped: %2d -> Size: %2d | %s%n",
                    popped, stack.size(), stack.toString());
        }

        System.out.println("\nAdding more elements after popping:");
        for (int i = 100; i <= 103; i++) {
            stack.push(i);
            System.out.printf("Pushed %3d -> Size: %2d | %s%n",
                    i, stack.size(), stack.toString());
        }

        System.out.println("\n=== Memory Efficiency Demonstration ===");

        // Create a new stack and show bulk operations
        MyStackArrayListImpl<String> stringStack = new MyStackArrayListImpl<>();

        System.out.println("Adding 1000 string elements to demonstrate scaling:");
        long startTime = System.nanoTime();

        for (int i = 1; i <= 1000; i++) {
            stringStack.push("Element-" + i);
        }

        long endTime = System.nanoTime();
        double durationMs = (endTime - startTime) / 1_000_000.0;

        System.out.printf("Added 1000 elements in %.2f ms%n", durationMs);
        System.out.println("Final stack size: " + stringStack.size());
        System.out.println("Top 3 elements:");
        for (int i = 0; i < 3; i++) {
            System.out.println("  " + stringStack.pop());
        }

        System.out.println("\n=== Array Conversion After Resizing ===");
        Object[] stackArray = stack.toArray();
        System.out.println("Stack converted to array (length: " + stackArray.length + "):");
        System.out.print("Array contents: [");
        for (int i = 0; i < stackArray.length; i++) {
            System.out.print(stackArray[i]);
            if (i < stackArray.length - 1) System.out.print(", ");
        }
        System.out.println("]");

        System.out.println("\n=== Key Points About Dynamic Resizing ===");
        System.out.println("1. ArrayList starts with default capacity (usually 10)");
        System.out.println("2. When capacity is exceeded, it creates a larger array (typically 1.5x)");
        System.out.println("3. All existing elements are copied to the new array");
        System.out.println("4. This happens automatically - no manual intervention needed");
        System.out.println("5. Amortized time complexity for push() remains O(1)");
        System.out.println("6. Occasional O(n) operations during resize are spread across many O(1) operations");
    }

}
