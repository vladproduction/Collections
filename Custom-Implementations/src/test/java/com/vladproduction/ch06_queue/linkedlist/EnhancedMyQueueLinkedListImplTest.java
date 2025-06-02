package com.vladproduction.ch06_queue.linkedlist;

import com.vladproduction.ch06_queue.MyQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Enhanced Queue LinkedList-based Implementation Tests")
public class EnhancedMyQueueLinkedListImplTest {

    private MyQueue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new MyQueueLinkedListImpl<>();
    }

    @Nested
    @DisplayName("Basic Operations Tests")
    class BasicOperationsTests {

        @Test
        @DisplayName("isEmpty() returns true initially")
        void testIsEmptyInitially() {
            assertTrue(queue.isEmpty());
            assertEquals(0, queue.size());
        }

        @Test
        @DisplayName("enQueue and size() work properly")
        void testEnQueueAndSize() {
            queue.enQueue(10);
            queue.enQueue(20);
            assertEquals(2, queue.size());
            assertFalse(queue.isEmpty());
        }

        @Test
        @DisplayName("deQueue and peek() work properly")
        void testPeekAndDeQueue() {
            queue.enQueue(5);
            queue.enQueue(10);
            assertEquals(5, queue.peek());
            assertEquals(5, queue.deQueue());
            assertEquals(10, queue.peek());
        }

        @Test
        @DisplayName("FIFO order is maintained correctly")
        void testFIFOOrder() {
            // Enqueue sequence
            for (int i = 1; i <= 5; i++) {
                queue.enQueue(i * 10);
            }

            // Dequeue and verify order
            assertEquals(10, queue.deQueue());
            assertEquals(20, queue.deQueue());
            assertEquals(30, queue.deQueue());

            // Add more elements
            queue.enQueue(60);
            queue.enQueue(70);

            // Continue dequeue and verify order
            assertEquals(40, queue.deQueue());
            assertEquals(50, queue.deQueue());
            assertEquals(60, queue.deQueue());
            assertEquals(70, queue.deQueue());
        }

        @Test
        @DisplayName("single element operations work correctly")
        void testSingleElementOperations() {
            queue.enQueue(42);
            assertEquals(1, queue.size());
            assertEquals(42, queue.peek());
            assertEquals(42, queue.deQueue());
            assertTrue(queue.isEmpty());
        }
    }

    @Nested
    @DisplayName("Exception Handling Tests")
    class ExceptionHandlingTests {

        @Test
        @DisplayName("throwing exception during deQueue on empty Queue")
        void testDeQueueOnEmptyThrows() {
            IllegalStateException exception = assertThrows(IllegalStateException.class, () -> queue.deQueue());
            assertEquals("Queue is empty", exception.getMessage());
        }

        @Test
        @DisplayName("throwing exception during peek on empty Queue")
        void testPeekOnEmptyThrows() {
            IllegalStateException exception = assertThrows(IllegalStateException.class, () -> queue.peek());
            assertEquals("Queue is empty", exception.getMessage());
        }

        @Test
        @DisplayName("enQueue throws exception for null item")
        void testEnQueueNullThrowsException() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> queue.enQueue(null));
            assertEquals("Item cannot be null", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Null Handling Tests")
    class NullHandlingTests {

        @Test
        @DisplayName("queue rejects null values on enQueue")
        void testNullRejection() {
            // LinkedList implementation should reject nulls
            assertThrows(IllegalArgumentException.class, () -> queue.enQueue(null));

            // Queue should remain empty after failed null enqueue
            assertTrue(queue.isEmpty());
            assertEquals(0, queue.size());
        }

        @Test
        @DisplayName("contains works correctly when searching for null")
        void testContainsWithNull() {
            queue.enQueue(1);
            queue.enQueue(2);

            // Should return false for null since nulls can't be added
            assertFalse(queue.contains(null));
        }

        @Test
        @DisplayName("contains handles null search on empty queue")
        void testContainsNullOnEmptyQueue() {
            assertFalse(queue.contains(null));
        }
    }

    @Nested
    @DisplayName("Utility Methods Tests")
    class UtilityMethodsTests {

        @Test
        @DisplayName("clear() works correctly")
        void testClear() {
            queue.enQueue(1);
            queue.enQueue(2);
            queue.clear();
            assertTrue(queue.isEmpty());
            assertEquals(0, queue.size());
        }

        @Test
        @DisplayName("clear on empty queue does nothing")
        void testClearOnEmptyQueue() {
            queue.clear();
            assertTrue(queue.isEmpty());
            assertEquals(0, queue.size());
        }

        @Test
        @DisplayName("multiple clear operations work correctly")
        void testMultipleClearOperations() {
            queue.enQueue(1);
            queue.clear();
            queue.enQueue(2);
            queue.clear();
            queue.clear(); // Clear empty queue

            assertTrue(queue.isEmpty());
            assertEquals(0, queue.size());
        }

        @Test
        @DisplayName("contains returns respective result")
        void testContains() {
            queue.enQueue(100);
            queue.enQueue(200);
            queue.enQueue(300);

            assertTrue(queue.contains(100));
            assertTrue(queue.contains(200));
            assertTrue(queue.contains(300));
            assertFalse(queue.contains(400));
            assertFalse(queue.contains(0));
        }

        @Test
        @DisplayName("contains works correctly after deQueue operations")
        void testContainsAfterDeQueue() {
            queue.enQueue(1);
            queue.enQueue(2);
            queue.enQueue(3);

            assertTrue(queue.contains(1));
            queue.deQueue(); // Remove 1
            assertFalse(queue.contains(1));
            assertTrue(queue.contains(2));
            assertTrue(queue.contains(3));
        }

        @Test
        @DisplayName("toArray() creates correct array")
        void testToArray() {
            queue.enQueue(1);
            queue.enQueue(2);
            queue.enQueue(3);

            Object[] array = queue.toArray();
            assertNotNull(array);
            assertEquals(3, array.length);
            assertArrayEquals(new Object[]{1, 2, 3}, array);
        }

        @Test
        @DisplayName("toArray on empty queue returns empty array")
        void testToArrayOnEmptyQueue() {
            Object[] array = queue.toArray();
            assertNotNull(array);
            assertEquals(0, array.length);
            assertArrayEquals(new Object[]{}, array);
        }

        @Test
        @DisplayName("toString method works correctly")
        void testToStringMethod() {
            // Empty queue
            assertEquals("Queue: [] ", queue.toString());

            // Single element
            queue.enQueue(42);
            assertEquals("Queue: [42] <- front", queue.toString());

            // Multiple elements
            queue.enQueue(100);
            queue.enQueue(200);
            assertEquals("Queue: [42, 100, 200] <- front", queue.toString());
        }
    }

    @Nested
    @DisplayName("Iterator Tests")
    class IteratorTests {

        @Test
        @DisplayName("iterator while hasNext() and next() normal behaviour")
        void testIteratorNormalUse() {
            queue.enQueue(1);
            queue.enQueue(2);
            queue.enQueue(3);

            Iterator<Integer> it = queue.iterator();
            assertTrue(it.hasNext());
            assertEquals(1, it.next());
            assertEquals(2, it.next());
            assertEquals(3, it.next());
            assertFalse(it.hasNext());
        }

        @Test
        @DisplayName("iterator throws exception when queue is empty")
        void testIteratorOnEmpty() {
            Iterator<Integer> it = queue.iterator();
            assertFalse(it.hasNext());
            assertThrows(NoSuchElementException.class, it::next);
        }

        @Test
        @DisplayName("iterator throws exception when removing (unsupported operation)")
        void testIteratorUnsupportedRemove() {
            queue.enQueue(1);
            Iterator<Integer> it = queue.iterator();
            assertThrows(UnsupportedOperationException.class, it::remove);
        }

        @Test
        @DisplayName("iterator exception message is correct")
        void testIteratorExceptionMessage() {
            Iterator<Integer> it = queue.iterator();
            NoSuchElementException exception = assertThrows(NoSuchElementException.class, it::next);
            assertEquals("No more elements in the queue", exception.getMessage());
        }

        @Test
        @DisplayName("multiple iterators work independently")
        void testMultipleIterators() {
            queue.enQueue(1);
            queue.enQueue(2);
            queue.enQueue(3);

            Iterator<Integer> iter1 = queue.iterator();
            Iterator<Integer> iter2 = queue.iterator();

            assertEquals(1, iter1.next());
            assertEquals(1, iter2.next());
            assertEquals(2, iter1.next());
            assertEquals(2, iter2.next());

            // Verify independent state
            assertTrue(iter1.hasNext());
            assertTrue(iter2.hasNext());
        }

        @Test
        @DisplayName("iterator works correctly after queue modifications")
        void testIteratorAfterModifications() {
            queue.enQueue(1);
            queue.enQueue(2);

            // Create iterator
            Iterator<Integer> it = queue.iterator();
            assertEquals(1, it.next());

            // Modify queue (note: this tests current behavior, not fail-fast)
            queue.enQueue(3);

            // Iterator should continue with original state
            assertEquals(2, it.next());
            assertTrue(it.hasNext());
        }
    }

    @Nested
    @DisplayName("Performance and Scalability Tests")
    class PerformanceTests {

        @Test
        @DisplayName("large dataset handling")
        void testLargeDataSet() {
            int elementCount = 10000;

            // Enqueue large number of elements
            for (int i = 0; i < elementCount; i++) {
                queue.enQueue(i);
            }

            assertEquals(elementCount, queue.size());

            // Dequeue all and verify order
            for (int i = 0; i < elementCount; i++) {
                assertEquals(i, queue.deQueue());
            }

            assertTrue(queue.isEmpty());
        }

        @Test
        @DisplayName("stress test with alternating enqueue and dequeue")
        void testStressAlternatingOperations() {
            int operations = 1000;

            for (int i = 0; i < operations; i++) {
                queue.enQueue(i);
                if (i % 3 == 0 && !queue.isEmpty()) {
                    queue.deQueue();
                }
            }

            // Verify queue is in consistent state
            assertFalse(queue.isEmpty());
            assertFalse(queue.isEmpty());

            // Verify FIFO order is maintained
            Integer first = queue.peek();
            Integer dequeued = queue.deQueue();
            assertEquals(first, dequeued);
        }

        @Test
        @DisplayName("memory efficiency test")
        void testMemoryEfficiency() {
            // Test that LinkedList doesn't pre-allocate memory
            MyQueue<Integer> emptyQueue = new MyQueueLinkedListImpl<>();
            assertTrue(emptyQueue.isEmpty());

            // Add and remove elements multiple times
            for (int cycle = 0; cycle < 10; cycle++) {
                for (int i = 0; i < 100; i++) {
                    emptyQueue.enQueue(i);
                }

                for (int i = 0; i < 100; i++) {
                    emptyQueue.deQueue();
                }

                assertTrue(emptyQueue.isEmpty());
            }
        }
    }

    @Nested
    @DisplayName("Edge Cases and Boundary Tests")
    class EdgeCasesTests {

        @Test
        @DisplayName("queue behavior after complete drain and refill")
        void testDrainAndRefill() {
            // Fill queue
            for (int i = 1; i <= 5; i++) {
                queue.enQueue(i);
            }

            // Completely drain
            while (!queue.isEmpty()) {
                queue.deQueue();
            }

            assertTrue(queue.isEmpty());
            assertEquals(0, queue.size());

            // Refill and verify functionality
            queue.enQueue(100);
            queue.enQueue(200);
            assertEquals(2, queue.size());
            assertEquals(100, queue.peek());
        }

        @Test
        @DisplayName("head and tail pointer consistency")
        void testHeadTailConsistency() {
            // Test single element (head == tail scenario)
            queue.enQueue(1);
            assertEquals(1, queue.peek());
            assertEquals(1, queue.deQueue());
            assertTrue(queue.isEmpty());

            // Test adding after empty
            queue.enQueue(2);
            assertEquals(2, queue.peek());

            // Test multiple elements
            queue.enQueue(3);
            queue.enQueue(4);
            assertEquals(2, queue.deQueue()); // Should be first added

        }
    }
}
