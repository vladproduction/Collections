package com.vladproduction.ch04_stack.queue;

import com.vladproduction.ch04_stack.MyStackTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * MyStackQueueImplTest - Unique tests for queue implementation:
 *      LIFO behavior despite queue internals
 *      Mixed operations (push/pop alternating)
 *      Performance with O(n) push operations
 *      Internal queue management
 * */
@DisplayName("Queue Stack Implementation Tests")
public class MyStackQueueImplTest extends MyStackTestBase<MyStackQueueImpl<Integer>> {

    @Override
    protected MyStackQueueImpl<Integer> createStack() {
        return new MyStackQueueImpl<>();
    }

    @Test
    @DisplayName("Queue - LIFO behavior with queue internals")
    void testLIFOBehaviorWithQueueInternals() {
        // This tests that despite using queues internally,
        // the stack maintains LIFO behavior
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        // Should pop in reverse order
        assertEquals(5, stack.pop());
        assertEquals(4, stack.pop());
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());

        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("Queue - Performance with multiple pushes")
    void testPerformanceWithMultiplePushes() {
        // Queue implementation has O(n) push, so test with moderate number
        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }

        assertEquals(100, stack.size());
        assertEquals(99, stack.peek());

        // Pop some elements
        for (int i = 99; i >= 90; i--) {
            assertEquals(i, stack.pop());
        }

        assertEquals(90, stack.size());
        assertEquals(89, stack.peek());
    }

    @Test
    @DisplayName("Queue - Mixed operations")
    void testMixedOperations() {
        // Test alternating push/pop to stress the queue rearrangement
        stack.push(1);
        assertEquals(1, stack.pop());

        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());

        stack.push(4);
        assertEquals(4, stack.peek());
        assertEquals(4, stack.pop());
        assertEquals(2, stack.pop());

        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("Queue - toString format")
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

    @Test
    @DisplayName("Queue - Clear both internal queues")
    void testClearBothInternalQueues() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertFalse(stack.isEmpty());

        stack.clear();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());

        // Should be able to use normally after clear
        stack.push(10);
        assertEquals(10, stack.peek());
    }


}






















