package com.vladproduction.ch06_queue.array;

import com.vladproduction.ch06_queue.MyQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Queue Array-based Implementation Tests")
class MyQueueArrayImplTest {

    private MyQueue<Integer> intQueue;

    @BeforeEach
    void setUp() {
        intQueue = new MyQueueArrayImpl<>();
    }

    @Test
    @DisplayName("isEmptyReturnCorrectlyForNewInitiallyQueue")
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
    @DisplayName("throwing exception during peek on empty Queue")
    void testThrowingExceptionDuringPeekOnEmptyQueue() {
        assertThrows(RuntimeException.class, () -> intQueue.peek());
        //or
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> intQueue.peek());
        assertEquals("Queue is empty", exception.getMessage());
    }

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
    @DisplayName("contain return respective result")
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
    @DisplayName("toArray is create correct array")
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
    @DisplayName("iterator methods hasNext() and next() works correct")
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

}