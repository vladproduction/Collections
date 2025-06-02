package com.vladproduction.ch04_stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Contains common tests that all implementations must pass
 * Ensures consistent behavior across all implementations
 * Tests: basic operations, LIFO order, error conditions, edge cases
 * */
public abstract class MyStackTestBase<T extends MyStack<Integer>> {

    protected T stack;

    //abstract method to be implemented by each implementation class
    protected abstract T createStack();

    @BeforeEach
    public void setUp() {
        stack = createStack();
    }

    @Test
    @DisplayName("New stack should be empty")
    void testNewStackIsEmpty() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    @DisplayName("Push single element")
    void testPushSingleElement() {
        stack.push(10);
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
        assertEquals(10, stack.peek());
    }

    @Test
    @DisplayName("Push multiple elements")
    void testPushMultipleElements() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(3, stack.size());
        assertEquals(30, stack.peek());
        assertFalse(stack.isEmpty());
    }

    @Test
    @DisplayName("Pop single element")
    void testPopSingleElement() {
        stack.push(42);
        Integer popped = stack.pop();

        assertEquals(42, popped);
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    @DisplayName("Pop maintains LIFO order")
    void testPopLIFOOrder() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("Peek does not remove element")
    void testPeekDoesNotRemove() {
        stack.push(100);

        assertEquals(100, stack.peek());
        assertEquals(100, stack.peek());
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());
    }

    @Test
    @DisplayName("Pop from empty stack throws exception")
    void testPopFromEmptyStack() {
        assertThrows(RuntimeException.class, () -> stack.pop());
    }

    @Test
    @DisplayName("Peek at empty stack throws exception")
    void testPeekAtEmptyStack() {
        assertThrows(RuntimeException.class, () -> stack.peek());
    }

    @Test
    @DisplayName("Contains existing element")
    void testContainsExistingElement() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertTrue(stack.contains(10));
        assertTrue(stack.contains(20));
        assertTrue(stack.contains(30));
    }

    @Test
    @DisplayName("Does not contain non-existing element")
    void testDoesNotContainNonExistingElement() {
        stack.push(10);
        stack.push(20);

        assertFalse(stack.contains(30));
        assertFalse(stack.contains(0));
    }

    @Test
    @DisplayName("Contains returns false for empty stack")
    void testContainsOnEmptyStack() {
        assertFalse(stack.contains(10));
    }

    @Test
    @DisplayName("Clear removes all elements")
    void testClear() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack.clear();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        assertFalse(stack.contains(1));
    }

    @Test
    @DisplayName("Clear on empty stack")
    void testClearOnEmptyStack() {
        stack.clear();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    @DisplayName("toArray returns correct array")
    void testToArray() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        Object[] array = stack.toArray();

        assertNotNull(array);
        assertEquals(3, array.length);
        // Note: Different implementations may have different array ordering
        // This test just checks that all elements are present
        assertTrue(arrayContains(array, 1));
        assertTrue(arrayContains(array, 2));
        assertTrue(arrayContains(array, 3));
    }

    @Test
    @DisplayName("toArray on empty stack")
    void testToArrayOnEmptyStack() {
        Object[] array = stack.toArray();
        assertNotNull(array);
        assertEquals(0, array.length);
    }

    @Test
    @DisplayName("Push null element")
    void testPushNullElement() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> stack.push(null)
        );
        assertEquals("Element cannot be null", exception.getMessage());

    }

    @Test
    @DisplayName("Complex operations sequence")
    void testComplexOperationsSequence() {
        // Push some elements
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.size());

        // Pop one
        assertEquals(2, stack.pop());
        assertEquals(1, stack.size());

        // Push more
        stack.push(3);
        stack.push(4);
        assertEquals(3, stack.size());

        // Check peek
        assertEquals(4, stack.peek());
        assertEquals(3, stack.size());

        // Pop all
        assertEquals(4, stack.pop());
        assertEquals(3, stack.pop());
        assertEquals(1, stack.pop());

        assertTrue(stack.isEmpty());
    }

    // Helper method to check if array contains element
    private boolean arrayContains(Object[] array, Object element) {
        for (Object obj : array) {
            if (obj != null && obj.equals(element)) {
                return true;
            }
        }
        return false;
    }


}
























