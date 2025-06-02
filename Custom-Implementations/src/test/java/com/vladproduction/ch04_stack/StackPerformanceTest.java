package com.vladproduction.ch04_stack;

import com.vladproduction.ch04_stack.array.MyStackArrayImpl;
import com.vladproduction.ch04_stack.arraylist.MyStackArrayListImpl;
import com.vladproduction.ch04_stack.linkedlist.MyStackLinkedListImpl;
import com.vladproduction.ch04_stack.queue.MyStackQueueImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("Performance Comparison Tests")
public class StackPerformanceTest {

    private static final int LARGE_SIZE = 10000;

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    @DisplayName("ArrayList - Performance test")
    void testArrayListPerformance() {
        MyStack<Integer> stack = new MyStackArrayListImpl<>();
        performanceTest(stack, "ArrayList");
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    @DisplayName("LinkedList - Performance test")
    void testLinkedListPerformance() {
        MyStack<Integer> stack = new MyStackLinkedListImpl<>();
        performanceTest(stack, "LinkedList");
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    @DisplayName("Array - Performance test")
    void testArrayPerformance() {
        MyStack<Integer> stack = new MyStackArrayImpl<>(LARGE_SIZE);
        performanceTest(stack, "Array");
    }

    @Test
    @Timeout(value = 30, unit = TimeUnit.SECONDS) // Queue is slower
    @DisplayName("Queue - Performance test")
    void testQueuePerformance() {
        MyStack<Integer> stack = new MyStackQueueImpl<>();
        performanceTest(stack, "Queue", 1000); // Use smaller size for queue
    }

    private void performanceTest(MyStack<Integer> stack, String type) {
        performanceTest(stack, type, LARGE_SIZE);
    }

    private void performanceTest(MyStack<Integer> stack, String type, int size) {
        long startTime = System.currentTimeMillis();

        // Push elements
        for (int i = 0; i < size; i++) {
            stack.push(i);
        }

        // Pop half
        for (int i = 0; i < size / 2; i++) {
            stack.pop();
        }

        long endTime = System.currentTimeMillis();

        System.out.println(type + " implementation: " + (endTime - startTime) + "ms for " + size + " operations");

        // Verify correctness
        assertEquals(size / 2, stack.size());
        assertFalse(stack.isEmpty());
    }

}
