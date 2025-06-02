package com.vladproduction.ch04_stack.array;

import com.vladproduction.ch04_stack.MyStackTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * MyStackArrayImplTest - Unique tests for array implementation:
 *      Capacity management (getCapacity(), isFull())
 *      Stack overflow exception handling
 *      Constructor variations (default vs custom capacity)
 *      Clear and reuse functionality
 * */
@DisplayName("Array Stack Implementation Tests")
public class MyStackArrayImplTest extends MyStackTestBase<MyStackArrayImpl<Integer>> {

    @Override
    protected MyStackArrayImpl<Integer> createStack() {
        return new MyStackArrayImpl<>(10);                       //capacity for testing is defined here
    }

    @Test
    @DisplayName("Array - Default capacity constructor")
    void testDefaultCapacityConstructor() {
        MyStackArrayImpl<Integer> defaultStack = new MyStackArrayImpl<>();
        assertEquals(10, defaultStack.getCapacity());
        assertTrue(defaultStack.isEmpty());
    }

    @Test
    @DisplayName("Array - Custom capacity constructor")
    void testCustomCapacityConstructor() {
        MyStackArrayImpl<Integer> customStack = new MyStackArrayImpl<>(5);
        assertEquals(5, customStack.getCapacity());
        assertTrue(customStack.isEmpty());
    }

    @Test
    @DisplayName("Array - isFull functionality")
    void testIsFullFunctionality() {
        MyStackArrayImpl<Integer> smallStack = new MyStackArrayImpl<>(3);

        assertFalse(smallStack.isFull());

        smallStack.push(1);
        assertFalse(smallStack.isFull());

        smallStack.push(2);
        assertFalse(smallStack.isFull());

        smallStack.push(3);
        assertTrue(smallStack.isFull());
    }

    @Test
    @DisplayName("Array - Stack overflow exception")
    void testStackOverflowException() {
        MyStackArrayImpl<Integer> smallStack = new MyStackArrayImpl<>(2);

        smallStack.push(1);
        smallStack.push(2);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            smallStack.push(3);
        });

        assertTrue(exception.getMessage().contains("Stack overflow"));
    }

    @Test
    @DisplayName("Array - Capacity management")
    void testCapacityManagement() {
        MyStackArrayImpl<Integer> stack5 = new MyStackArrayImpl<>(5);

        for (int i = 1; i <= 5; i++) {
            stack5.push(i);
        }

        assertEquals(5, stack5.size());
        assertEquals(5, stack5.getCapacity());
        assertTrue(stack5.isFull());

        // Should not be able to add more
        assertThrows(RuntimeException.class, () -> stack5.push(6));
    }

    @Test
    @DisplayName("Array - toString with capacity info")
    void testToStringWithCapacity() {
        MyStackArrayImpl<Integer> testStack = new MyStackArrayImpl<>(5);
        testStack.push(1);
        testStack.push(2);

        String result = testStack.toString();
        assertTrue(result.contains("Stack"));
        assertTrue(result.contains("1"));
        assertTrue(result.contains("2"));
        assertTrue(result.contains("2/5")); // size/capacity
    }

    @Test
    @DisplayName("Array - Clear and reuse")
    void testClearAndReuse() {
        MyStackArrayImpl<Integer> testStack = new MyStackArrayImpl<>(3);

        // Fill to capacity
        testStack.push(1);
        testStack.push(2);
        testStack.push(3);
        assertTrue(testStack.isFull());

        // Clear
        testStack.clear();
        assertFalse(testStack.isFull());
        assertTrue(testStack.isEmpty());
        assertEquals(3, testStack.getCapacity()); // Capacity unchanged

        // Should be able to use again
        testStack.push(10);
        assertEquals(1, testStack.size());
        assertEquals(10, testStack.peek());
    }


}


















