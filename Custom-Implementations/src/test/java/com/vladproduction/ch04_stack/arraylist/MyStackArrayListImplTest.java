package com.vladproduction.ch04_stack.arraylist;

import com.vladproduction.ch04_stack.MyStackTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * MyStackArrayListImplTest
 *      Tests dynamic resizing with large datasets
 *      Verifies toString format
 *      Inherits all base tests
 * */
@DisplayName("ArrayList Stack Implementation Tests")
public class MyStackArrayListImplTest extends MyStackTestBase<MyStackArrayListImpl<Integer>> {

    @Override
    protected MyStackArrayListImpl<Integer> createStack() {
        return new MyStackArrayListImpl<>();
    }

    @Test
    @DisplayName("ArrayList - Large number of elements")
    void testLargeNumberOfElements() {
        // Test with many elements to verify dynamic resizing
        for (int i = 0; i < 1000; i++) {
            stack.push(i);
        }

        assertEquals(1000, stack.size());
        assertEquals(999, stack.peek());

        // Pop half
        for (int i = 0; i < 500; i++) {
            stack.pop();
        }

        assertEquals(500, stack.size());
        assertEquals(499, stack.peek());
    }

    @Test
    @DisplayName("ArrayList - toString format")
    void testToStringFormat() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        String result = stack.toString();
        assertTrue(result.contains("Stack"));
        assertTrue(result.contains("1"));
        assertTrue(result.contains("2"));
        assertTrue(result.contains("3"));
    }
}