package com.vladproduction.ch04_stack;

public interface MyStack<T> {

    void push(T element);                 //push an element onto the top of the stack

    T pop();                              //removes and returns the top element from the stack (exception if empty)

    T peek();                             //returns the top element from the stack (exception if empty)

    boolean isEmpty();                    //returns true if the stack is empty, false otherwise

    int size();                           //returns the number of elements in the stack (0 if empty)

    void clear();                         //removes all elements from the stack

    boolean contains(T element);          //returns true if the stack contains the specified element, false otherwise

    Object[] toArray();                   //returns an array containing all elements in the stack

}
