package com.vladproduction.app02.list.linkedlist.mydoublylinkedlist;

public class MyDoublyLinkedList<T> {

    private DoublyNode<T> head;
    private DoublyNode<T> tail;
    private int size = 0;

    //add to end method:
    public void add(T data) {
        DoublyNode<T> newNode = new DoublyNode<>(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    //add to the beginning method:
    public void addFirst(T data) {
        DoublyNode<T> newNode = new DoublyNode<>(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    //remove bu value method:
    public boolean remove(T data) {
        DoublyNode<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {

                //head
                if(current.prev != null){
                    current.prev.next = current.next;
                }else {
                    head = current.next;
                }

                //tail
                if(current.next != null){
                    current.next.prev = current.prev;
                }else {
                    tail = current.prev;
                }

                size--;
                return true;

            }
            current = current.next;
        }
        return false;
    }

    // Get by index
    public T get(int index) {
        checkBounds(index);
        DoublyNode<T> current = head;
        for (int i = 0; i < index; i++) current = current.next;
        return current.data;
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }

    // Print forward
    public void printForward() {
        DoublyNode<T> current = head;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Print backward
    public void printBackward() {
        DoublyNode<T> current = tail;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.prev;
        }
        System.out.println("null");
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // Clear the list
    public void clear() {
        head = tail = null;
        size = 0;
    }

    // Create a custom iterator
    public DoublyIterator<T> iterator() {
        return new DoublyIterator<>(head);
    }

    public DoublyIterator<T> reverseIterator() {
        return new DoublyIterator<>(tail, true);
    }


}























