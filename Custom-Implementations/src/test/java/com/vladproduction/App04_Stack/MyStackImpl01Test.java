package com.vladproduction.App04_Stack;


import org.junit.Assert;
import org.junit.Test;
import com.vladproduction.stack.MyStack;
import com.vladproduction.stack.MyStackImpl02_LinkedList;

public class MyStackImpl01Test {

    @Test
    public void apiTest(){
        //01 []
        //02 LinkedList
        //if we change 01-->02: now is_a LinkedList
        MyStack myStack = new MyStackImpl02_LinkedList();
        Assert.assertTrue(myStack.isEmpty());
        Assert.assertTrue(myStack.size()==0);
        myStack.push("a");
        myStack.push("b");
        myStack.push("c");
        myStack.push("d");
        Assert.assertFalse(myStack.isEmpty());
        Assert.assertTrue(myStack.size()==4);
        Object topItem = myStack.peek();
        Assert.assertTrue(myStack.size()==4);
        Assert.assertEquals(topItem,"d");
        //для убеждения когда вызываем метод пик, он не удаляется и правильно возвращает его значение
        topItem = myStack.peek();
        Assert.assertTrue(myStack.size()==4);
        Assert.assertEquals(topItem,"d");

        //pop
        topItem = myStack.pop();
        Assert.assertTrue(myStack.size()==3);
        Assert.assertEquals(topItem,"d");
        //check if everything ok work
        topItem = myStack.peek();
        Assert.assertTrue(myStack.size()==3);
        Assert.assertEquals(topItem,"c");
    }
}
