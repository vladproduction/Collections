package com.vladproduction.ch06_queue.array;

import com.vladproduction.ch06_queue.MyQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Enhanced Queue Array-based Implementation Tests")
public class EnhancedMyQueueArrayImplTest {

    private MyQueue<Integer> intQueue;

    @BeforeEach
    void setUp() {
        intQueue = new MyQueueArrayImpl<>();
    }

    @Nested
    @DisplayName("Basic Operations Tests")
    class BasicOperationsTests {

        @Test
        @DisplayName("isEmpty returns correctly for new initially queue")
        void testIsEmptyReturnCorrectlyForNewInitiallyQueue() {
            assertTrue(intQueue.isEmpty());
            assertEquals(0, intQueue.size());
        }

        @Test
        @DisplayName("enQueue and size work properly")
        void testEnQueueAndSizeWorkProperly() {
            intQueue.enQueue(1);
            intQueue.enQueue(2);
            intQueue.enQueue(3);

            assertEquals(3, intQueue.size());
            assertFalse(intQueue.isEmpty());
        }

        @Test
        @DisplayName("peek and deQueue work properly")
        void testPeekAndDeQueueWorkProperly() {
            intQueue.enQueue(5);
            intQueue.enQueue(10);

            assertEquals(5, intQueue.peek());
            assertEquals(5, intQueue.deQueue());
            assertEquals(10, intQueue.peek());
            assertEquals(10, intQueue.deQueue());
        }

        @Test
        @DisplayName("FIFO order is maintained correctly")
        void testFIFOOrder() {
            // Enqueue sequence
            for (int i = 1; i <= 5; i++) {
                intQueue.enQueue(i * 10);
            }

            // Dequeue and verify order
            assertEquals(10, intQueue.deQueue());
            assertEquals(20, intQueue.deQueue());
            assertEquals(30, intQueue.deQueue());

            // Add more elements
            intQueue.enQueue(60);
            intQueue.enQueue(70);

            // Continue dequeue and verify order
            assertEquals(40, intQueue.deQueue());
            assertEquals(50, intQueue.deQueue());
            assertEquals(60, intQueue.deQueue());
            assertEquals(70, intQueue.deQueue());
        }
    }

    @Nested
    @DisplayName("Exception Handling Tests")
    class ExceptionHandlingTests {

        @Test
        @DisplayName("throwing exception during peek on empty Queue")
        void testThrowingExceptionDuringPeekOnEmptyQueue() {
            assertThrows(RuntimeException.class, () -> intQueue.peek());

            IllegalStateException exception = assertThrows(IllegalStateException.class, () -> intQueue.peek());
            assertEquals("Queue is empty", exception.getMessage());
        }

        @Test
        @DisplayName("throwing exception during deQueue on empty Queue")
        void testThrowingExceptionDuringDeQueueOnEmptyQueue() {
            IllegalStateException exception = assertThrows(IllegalStateException.class, () -> intQueue.deQueue());
            assertEquals("Queue is empty", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Capacity and Constructor Tests")
    class CapacityAndConstructorTests {

        @Test
        @DisplayName("default constructor creates queue with capacity 5")
        void testDefaultConstructor() {
            MyQueue<String> queue = new MyQueueArrayImpl<>();
            assertTrue(queue.isEmpty());
            assertEquals(0, queue.size());
        }

        @Test
        @DisplayName("parameterized constructor works with custom capacity")
        void testParameterizedConstructor() {
            MyQueue<String> queue = new MyQueueArrayImpl<>(10);
            assertTrue(queue.isEmpty());
            assertEquals(0, queue.size());

            // Fill beyond default capacity to test custom capacity
            for (int i = 0; i < 8; i++) {
                queue.enQueue("Item" + i);
            }
            assertEquals(8, queue.size());
        }

        @Test
        @DisplayName("constructor throws exception for negative capacity")
        void testConstructorWithNegativeCapacity() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> new MyQueueArrayImpl<>(-1));
            assertEquals("Capacity cannot be negative", exception.getMessage());
        }

        @Test
        @DisplayName("constructor handles zero capacity")
        void testConstructorWithZeroCapacity() {
            MyQueue<String> queue = new MyQueueArrayImpl<>(0);
            assertTrue(queue.isEmpty());

            // Should be able to add elements (will trigger resize)
            queue.enQueue("test");
            assertEquals(1, queue.size());
            assertEquals("test", queue.peek());
        }

        @Test
        @DisplayName("array resizing works correctly when capacity exceeded")
        void testArrayResizing() {
            // Create queue with small capacity
            MyQueue<Integer> smallQueue = new MyQueueArrayImpl<>(2);

            // Add more elements than initial capacity
            for (int i = 1; i <= 8; i++) {
                smallQueue.enQueue(i);
            }

            assertEquals(8, smallQueue.size());

            // Verify all elements are present in correct order
            for (int i = 1; i <= 8; i++) {
                assertEquals(i, smallQueue.deQueue());
            }
        }

        @Test
        @DisplayName("large dataset handling")
        void testLargeDataSet() {
            MyQueue<Integer> largeQueue = new MyQueueArrayImpl<>();
            int elementCount = 1000;

            // Enqueue large number of elements
            for (int i = 0; i < elementCount; i++) {
                largeQueue.enQueue(i);
            }

            assertEquals(elementCount, largeQueue.size());

            // Dequeue all and verify order
            for (int i = 0; i < elementCount; i++) {
                assertEquals(i, largeQueue.deQueue());
            }

            assertTrue(largeQueue.isEmpty());
        }
    }

    @Nested
    @DisplayName("Null Handling Tests")
    class NullHandlingTests {

        @Test
        @DisplayName("queue can handle null values")
        void testNullValues() {
            MyQueue<String> stringQueue = new MyQueueArrayImpl<>();

            // Array implementation should allow nulls
            stringQueue.enQueue(null);
            stringQueue.enQueue("test");
            stringQueue.enQueue(null);

            assertEquals(3, stringQueue.size());
            assertNull(stringQueue.deQueue());
            assertEquals("test", stringQueue.deQueue());
            assertNull(stringQueue.deQueue());
        }

        @Test
        @DisplayName("contains works correctly with null values")
        void testContainsWithNull() {
            MyQueue<String> stringQueue = new MyQueueArrayImpl<>();

            stringQueue.enQueue("item1");
            stringQueue.enQueue(null);
            stringQueue.enQueue("item2");

            assertTrue(stringQueue.contains(null));
            assertTrue(stringQueue.contains("item1"));
            assertTrue(stringQueue.contains("item2"));
            assertFalse(stringQueue.contains("nonexistent"));
        }
    }

    @Nested
    @DisplayName("Utility Methods Tests")
    class UtilityMethodsTests {

        @Test
        @DisplayName("clear is working correctly")
        void testClearIsWorkingCorrectly() {
            intQueue.enQueue(1);
            intQueue.enQueue(2);
            intQueue.enQueue(3);

            intQueue.clear();
            assertEquals(0, intQueue.size());
            assertTrue(intQueue.isEmpty());
        }

        @Test
        @DisplayName("clear on empty queue does nothing")
        void testClearOnEmptyQueueDoesNothing() {
            intQueue.clear();
            assertEquals(0, intQueue.size());
        }

        @Test
        @DisplayName("multiple clear operations work correctly")
        void testMultipleClearOperations() {
            intQueue.enQueue(1);
            intQueue.clear();
            intQueue.enQueue(2);
            intQueue.clear();
            intQueue.clear(); // Clear empty queue

            assertTrue(intQueue.isEmpty());
            assertEquals(0, intQueue.size());
        }

        @Test
        @DisplayName("contains return respective result")
        void testContainReturnRespectiveResult() {
            intQueue.enQueue(1);
            intQueue.enQueue(2);
            intQueue.enQueue(3);

            assertTrue(intQueue.contains(1));
            assertTrue(intQueue.contains(2));
            assertTrue(intQueue.contains(3));

            assertFalse(intQueue.contains(4));
            assertFalse(intQueue.contains(0));
            assertFalse(intQueue.contains(-1));
            assertFalse(intQueue.contains(null));
        }

        @Test
        @DisplayName("toArray creates correct array")
        void testToArrayIsCreateCorrectArray() {
            intQueue.enQueue(1);
            intQueue.enQueue(2);
            intQueue.enQueue(3);

            Object[] array = intQueue.toArray();

            assertNotNull(array);
            assertEquals(3, array.length);
            assertArrayEquals(new Object[]{1, 2, 3}, array);
        }

        @Test
        @DisplayName("toArray on empty queue returns empty array")
        void testToArrayOnEmptyQueueReturnsEmptyArray() {
            Object[] array = intQueue.toArray();
            assertNotNull(array);
            assertEquals(0, array.length);
            assertArrayEquals(new Object[]{}, array);
        }

        @Test
        @DisplayName("toString method works correctly")
        void testToStringMethod() {
            // Empty queue
            assertEquals("Queue: []", intQueue.toString());

            // Single element
            intQueue.enQueue(42);
            assertEquals("Queue: [42] <- front", intQueue.toString());

            // Multiple elements
            intQueue.enQueue(100);
            intQueue.enQueue(200);
            assertEquals("Queue: [42, 100, 200] <- front", intQueue.toString());
        }
    }

    @Nested
    @DisplayName("Iterator Tests")
    class IteratorTests {

        @Test
        @DisplayName("iterator methods hasNext() and next() work correctly")
        void testIteratorMethodsHasNextAndNextWorksCorrect() {
            intQueue.enQueue(1);
            intQueue.enQueue(2);
            intQueue.enQueue(3);

            Iterator<Integer> iterator = intQueue.iterator();
            assertTrue(iterator.hasNext());
            assertEquals(1, iterator.next());
            assertTrue(iterator.hasNext());
            assertEquals(2, iterator.next());
            assertTrue(iterator.hasNext());
            assertEquals(3, iterator.next());
            assertFalse(iterator.hasNext());
            assertThrows(NoSuchElementException.class, iterator::next);
        }

        @Test
        @DisplayName("throwing exception while iterating empty queue")
        void testThrowingExceptionWhileIteratingEmptyQueue() {
            Iterator<Integer> iterator = intQueue.iterator();
            assertFalse(iterator.hasNext());
            assertThrows(NoSuchElementException.class, iterator::next);
        }

        @Test
        @DisplayName("iterator remove throws UnsupportedOperationException")
        void testIteratorRemoveThrowsException() {
            intQueue.enQueue(1);
            Iterator<Integer> iterator = intQueue.iterator();
            assertThrows(UnsupportedOperationException.class, iterator::remove);
        }

        @Test
        @DisplayName("multiple iterators work independently")
        void testMultipleIterators() {
            intQueue.enQueue(1);
            intQueue.enQueue(2);
            intQueue.enQueue(3);

            Iterator<Integer> iter1 = intQueue.iterator();
            Iterator<Integer> iter2 = intQueue.iterator();

            assertEquals(1, iter1.next());
            assertEquals(1, iter2.next());
            assertEquals(2, iter1.next());
            assertEquals(2, iter2.next());
        }

        @Test
        @DisplayName("iterator with null elements")
        void testIteratorWithNullElements() {
            MyQueue<String> stringQueue = new MyQueueArrayImpl<>();
            stringQueue.enQueue("first");
            stringQueue.enQueue(null);
            stringQueue.enQueue("third");

            Iterator<String> iterator = stringQueue.iterator();
            assertEquals("first", iterator.next());
            assertNull(iterator.next());
            assertEquals("third", iterator.next());
            assertFalse(iterator.hasNext());
        }
    }

    @Nested
    @DisplayName("Object Methods Tests")
    class ObjectMethodsTests {

        @Test
        @DisplayName("equals method works correctly")
        void testEqualsMethod() {
            MyQueueArrayImpl<Integer> queue1 = new MyQueueArrayImpl<>();
            MyQueueArrayImpl<Integer> queue2 = new MyQueueArrayImpl<>();

            // Empty queues should be equal
            assertEquals(queue1, queue2);

            // Add same elements to both
            queue1.enQueue(1);
            queue1.enQueue(2);
            queue2.enQueue(1);
            queue2.enQueue(2);

            assertEquals(queue1, queue2);

            // Different content should not be equal
            queue2.enQueue(3);
            assertNotEquals(queue1, queue2);
        }

        @Test
        @DisplayName("hashCode method works correctly")
        void testHashCodeMethod() {
            MyQueueArrayImpl<Integer> queue1 = new MyQueueArrayImpl<>();
            MyQueueArrayImpl<Integer> queue2 = new MyQueueArrayImpl<>();

            // Equal objects should have equal hash codes
            assertEquals(queue1.hashCode(), queue2.hashCode());

            queue1.enQueue(1);
            queue2.enQueue(1);
            assertEquals(queue1.hashCode(), queue2.hashCode());
        }

        @Test
        @DisplayName("equals handles null and different types")
        void testEqualsEdgeCases() {
            MyQueueArrayImpl<Integer> queue = new MyQueueArrayImpl<>();

            assertNotEquals(queue, null);
            assertNotEquals(queue, "not a queue");
            assertEquals(queue, queue); // reflexive
        }
    }

    @Nested
    @DisplayName("Comprehensive Integration Tests")
    class IntegrationTests {

        @Test
        @DisplayName("comprehensive queue operations sequence")
        void testComprehensiveOperations() {
            // Mixed operations test
            intQueue.enQueue(1);
            intQueue.enQueue(2);
            assertEquals(1, intQueue.deQueue());

            intQueue.enQueue(3);
            intQueue.enQueue(4);
            assertEquals(2, intQueue.peek());
            assertEquals(2, intQueue.deQueue());

            assertEquals(2, intQueue.size());
            assertTrue(intQueue.contains(3));

            intQueue.clear();
            assertTrue(intQueue.isEmpty());

            // Refill after clear
            intQueue.enQueue(10);
            intQueue.enQueue(20);
            assertEquals(2, intQueue.size());
            assertArrayEquals(new Object[]{10, 20}, intQueue.toArray());
        }

        @Test
        @DisplayName("queue operations after multiple clear and refill cycles")
        void testMultipleClearRefillCycles() {
            for (int cycle = 0; cycle < 3; cycle++) {
                // Fill queue
                for (int i = 1; i <= 5; i++) {
                    intQueue.enQueue(i + cycle * 10);
                }

                assertEquals(5, intQueue.size());

                // Partial dequeue
                intQueue.deQueue();
                intQueue.deQueue();
                assertEquals(3, intQueue.size());

                // Clear and verify
                intQueue.clear();
                assertTrue(intQueue.isEmpty());
            }

            // Final operation after cycles
            intQueue.enQueue(999);
            assertEquals(999, intQueue.peek());
        }

        @Test
        @DisplayName("stress test with alternating enqueue and dequeue")
        void testStressAlternatingOperations() {
            int operations = 100;

            for (int i = 0; i < operations; i++) {
                intQueue.enQueue(i);
                if (i % 3 == 0 && !intQueue.isEmpty()) {
                    intQueue.deQueue();
                }
            }

            // Verify queue is in consistent state
            assertFalse(intQueue.isEmpty());
            assertTrue(intQueue.size() > 0);

            // Verify FIFO order is maintained
            Integer first = intQueue.peek();
            Integer dequeued = intQueue.deQueue();
            assertEquals(first, dequeued);
        }
    }



}
