package com.vladproduction.app04.queue_deque_blockingqueue.queue.priorityqueue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.vladproduction.app04.queue_deque_blockingqueue.queue.priorityqueue.PriorityQueueStudentComparator.*;
import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueStudentComparatorTest {

    private PriorityQueueStudentComparator studentQueue;

    @BeforeEach
    void setUp() {
        studentQueue = new PriorityQueueStudentComparator();
        Student student1 = new Student(1, "John Doe", 3.8);
        Student student2 = new Student(2, "Jannet Doe", 4.0);
        Student student3 = new Student(3, "Bob Doe", 3.5);

        studentQueue.addStudent(student1);
        studentQueue.addStudent(student2);
        studentQueue.addStudent(student3);
    }

    @AfterEach
    void tearDown() {
        studentQueue = null;
    }

    @Test
    void add() {
        studentQueue.addStudent(new Student(4, "Jane Doe", 3.9));
        studentQueue.addStudent(new Student(5, "Jane Doe", 3.9));
        assertEquals(5, studentQueue.getSize());
        assertFalse(studentQueue.isEmpty());
    }

    @Test
    void removeStudent() {
        assertEquals(new Student(2, "Jannet Doe", 4.0), studentQueue.removeStudent());
        assertEquals(2, studentQueue.getSize());
        assertFalse(studentQueue.isEmpty());
    }

    @Test
    void isEmpty() {
        assertFalse(studentQueue.isEmpty());
        studentQueue.removeStudent();
        studentQueue.removeStudent();
        studentQueue.removeStudent();
        assertTrue(studentQueue.isEmpty());
    }

    @Test
    void getSize() {
        assertEquals(3, studentQueue.getSize());
    }

    @Test
    void peek() {
        assertEquals(studentQueue.peek(), studentQueue.removeStudent());
    }
}