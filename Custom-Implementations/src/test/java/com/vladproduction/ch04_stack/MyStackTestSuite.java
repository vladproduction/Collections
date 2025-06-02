package com.vladproduction.ch04_stack;

import com.vladproduction.ch04_stack.array.MyStackArrayImplTest;
import com.vladproduction.ch04_stack.arraylist.MyStackArrayListImplTest;
import com.vladproduction.ch04_stack.linkedlist.MyStackLinkedListImplTest;
import com.vladproduction.ch04_stack.queue.MyStackQueueImplTest;
import org.junit.jupiter.api.DisplayName;

// Test suite runner
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        MyStackArrayListImplTest.class,
        MyStackLinkedListImplTest.class,
        MyStackArrayImplTest.class,
        MyStackQueueImplTest.class
})
@DisplayName("MyStack Complete Test Suite")
public class MyStackTestSuite {

    // This class runs all stack implementation tests


}
