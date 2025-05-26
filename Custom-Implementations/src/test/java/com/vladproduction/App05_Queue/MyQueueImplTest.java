package com.vladproduction.App05_Queue;


import org.junit.Assert;
import org.junit.Test;
import com.vladproduction.queue.MyQueue;
import com.vladproduction.queue.MyQueueImpl;

public class MyQueueImplTest {

    @Test
    public void apiTest() {
        MyQueue queue = new MyQueueImpl();
        //isEmpty, size
        Assert.assertTrue(queue.isEmpty());
        Assert.assertTrue(queue.size() == 0);

        //enQueue(Object o), toString()
        queue.enQueue("a");
        queue.enQueue("b");
        queue.enQueue("c");
        queue.enQueue("d");
        queue.enQueue("e");
        Assert.assertFalse(queue.isEmpty());
        Assert.assertFalse(queue.size()==0);
        Assert.assertTrue(queue.size()==5);
        System.out.println("queue: "+queue);

        //capacity
        MyQueue queue1 = new MyQueueImpl(7);
        queue1.enQueue("a");
        queue1.enQueue("b");
        queue1.enQueue("c");
        queue1.enQueue("d");
        queue1.enQueue("e");
        queue1.enQueue("f");
        queue1.enQueue("g");
        Assert.assertTrue(queue1.size() == 7);
        Assert.assertFalse(queue1.size() == 5);
        System.out.println("queue1: " + queue1);

        //deQueue
        queue1.deQueue();
        Assert.assertTrue(queue1.size() == 6);
        Assert.assertFalse(queue1.size() == 7);
        System.out.println("queue1 (deQueue): " + queue1);

    }

    @Test
    public void hashCode_equalsTest() {
        MyQueue queue1 = new MyQueueImpl();
        queue1.enQueue("a");
        queue1.enQueue("b");
        queue1.enQueue("c");
        queue1.enQueue("d");
        queue1.enQueue("e");


        MyQueue queue = new MyQueueImpl();
        queue.enQueue("a");
        queue.enQueue("b");
        queue.enQueue("c");
        queue.enQueue("d");
        queue.enQueue("e");


        Assert.assertTrue(queue.hashCode() == queue1.hashCode());

        Assert.assertEquals(queue1, queue);
        Assert.assertEquals(queue, queue1);
        Assert.assertTrue(queue.equals(queue1));
        Assert.assertTrue(queue1.equals(queue));

        //not same data[] expected:

        MyQueue queue2 = new MyQueueImpl(7);
        queue2.enQueue("a");
        queue2.enQueue("b");
        queue2.enQueue("c");
        queue2.enQueue("d");
        queue2.enQueue("e");

        Assert.assertFalse(queue.hashCode() == queue2.hashCode());
        Assert.assertFalse(queue.equals(queue2));


    }
}
