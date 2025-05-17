package com.vladproduction.app04.queue_deque_blockingqueue.queue.linkedlist;

import com.vladproduction.app04.queue_deque_blockingqueue.queue.linkedlistqueue.StudentLinkedList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.vladproduction.app04.queue_deque_blockingqueue.queue.linkedlistqueue.StudentLinkedList.*;
import static org.junit.jupiter.api.Assertions.*;

class StudentLinkedListTest {

    private StudentLinkedList students;

    @BeforeEach
    void setUp() {
        students = new StudentLinkedList();
        students.addStudent(new Student(1, "John", 100));
        students.addStudent(new Student(2, "Bob", 95));
        students.addStudent(new Student(3, "Tom", 88));
    }

    @AfterEach
    void tearDown() {
        this.students = null;
    }

    @Test
    void addStudent() {
        students.addStudent(new Student(4, "Jane", 90));
        assertEquals(4, students.getSize());
    }

    @Test
    void getStudentByIndex() {
        assertEquals(new Student(2, "Bob", 95), students.getStudentByIndex(1));
    }

    @Test
    void getStudentById(){
        assertEquals(new Student(2, "Bob", 95), students.getStudentById(2));
    }

    @Test
    void getAllStudents() {
        assertEquals(3, students.getAllStudents().size());
    }

    @Test
    void updateStudent() {
        students.updateStudent(new Student(2, "Bob", 90));
        assertEquals(new Student(2, "Bob", 90), students.getStudentByIndex(1));
    }

    @Test
    void removeStudent() {
        students.removeStudent();
        assertEquals(2, students.getSize());
    }

    @Test
    void removeStudentByIndex() {
        students.removeStudentByIndex(0);
        students.removeStudentByIndex(1);
        assertEquals(1, students.getSize());
    }

    @Test
    void isEmpty() {
        assertFalse(students.isEmpty());
        students.removeStudent();
        students.removeStudent();
        students.removeStudent();
        assertTrue(students.isEmpty());
    }

    @Test
    void getSize() {
        assertEquals(3, students.getSize());
    }
}