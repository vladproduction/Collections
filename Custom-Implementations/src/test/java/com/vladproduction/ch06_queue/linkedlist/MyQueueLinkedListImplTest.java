package com.vladproduction.ch06_queue.linkedlist;

import com.vladproduction.ch06_queue.MyQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Queue LinkedList-based Implementation Tests")
class MyQueueLinkedListImplTest {

    private MyQueue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new MyQueueLinkedListImpl<>();
    }

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
    @DisplayName("throwing exception during deQueue on empty Queue")
    void testDeQueueOnEmptyThrows() {
        assertThrows(IllegalStateException.class, () -> queue.deQueue());
    }

    @Test
    @DisplayName("throwing exception during peek on empty Queue")
    void testPeekOnEmptyThrows() {
        assertThrows(IllegalStateException.class, () -> queue.peek());
    }

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
    @DisplayName("contains return respective result")
    void testContains() {
        queue.enQueue(100);
        queue.enQueue(200);
        assertTrue(queue.contains(100));
        assertFalse(queue.contains(300));
    }

    @Test
    @DisplayName("toArray() create correct array")
    void testToArray() {
        queue.enQueue(1);
        queue.enQueue(2);
        Object[] array = queue.toArray();
        assertArrayEquals(new Object[]{1, 2}, array);
    }

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

}