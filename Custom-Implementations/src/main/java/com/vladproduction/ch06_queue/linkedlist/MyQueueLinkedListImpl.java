package com.vladproduction.ch06_queue.linkedlist;

import com.vladproduction.ch06_queue.MyQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedList-based implementation of MyQueue
 * */
public class MyQueueLinkedListImpl<T> implements MyQueue<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MyQueueLinkedListImpl() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enQueue(T item) {
        if(item == null){
            throw new IllegalArgumentException("Item cannot be null");
        }
        Node<T> newNode = new Node<>(item);
        if(isEmpty()){
            head = newNode;
        }else{
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    @Override
    public T deQueue() {
        if(isEmpty()){
            throw new IllegalStateException("Queue is empty");
        }
        T item = head.value;
        head = head.next;
        if(head == null){
            tail = null;
        }
        size--;
        return item;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return head.value;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean contains(T item) {
        Node<T> current = head;
        while (current != null) {
            if ((item == null && current.value == null) || (item != null && item.equals(current.value))) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.value;
            current = current.next;
        }

        return array;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyQueueLinkedListIterator();
    }

    @Override
    public String toString() {
        if(isEmpty()){
            return "Queue: [] ";
        }
        StringBuilder sb = new StringBuilder("Queue: [");

        Node<T> current = head;
        while (current != null) {
            sb.append(current.value);
            if(current.next != null){
                sb.append(", ");
            }
            current = current.next;
        }

        sb.append("] <- front");
        return sb.toString();
    }

    private class MyQueueLinkedListIterator implements Iterator<T>{

        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException("No more elements in the queue");
            }
            T item = current.value;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported in this iterator");
        }

    }

    private static class Node<T>{

        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }

    }

}
