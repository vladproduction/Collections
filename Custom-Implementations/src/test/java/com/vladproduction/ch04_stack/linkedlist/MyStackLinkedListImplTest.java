package com.vladproduction.ch04_stack.linkedlist;

import com.vladproduction.ch04_stack.MyStackTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * MyStackLinkedListImplTest
 *      Tests efficient handling of large datasets
 *      Verifies proper LIFO order with many elements
 *      Tests toString format
 * */
@DisplayName("LinkedList Stack Implementation Tests")
public class MyStackLinkedListImplTest extends MyStackTestBase<MyStackLinkedListImpl<Integer>> {

    @Override
    protected MyStackLinkedListImpl<Integer> createStack() {
        return new MyStackLinkedListImpl<>();
    }

    @Test
    @DisplayName("LinkedList - Large number of elements")
    void testLargeNumberOfElements() {
        // LinkedList should handle large numbers efficiently
        for (int i = 0; i < 1000; i++) {
            stack.push(i);
        }

        assertEquals(1000, stack.size());
        assertEquals(999, stack.peek());

        // Pop all in reverse order
        for (int i = 999; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }

        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("LinkedList - toString format")
    void testToStringFormat() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        String result = stack.toString();
        assertTrue(result.contains("LinkedList Stack"));
        assertTrue(result.contains("1"));
        assertTrue(result.contains("2"));
        assertTrue(result.contains("3"));
    }

}