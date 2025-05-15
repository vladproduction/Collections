package com.vladproduction.app02.list.linkedlist.mydoublylinkedlist;

import java.util.NoSuchElementException;

public class DoublyIterator<T> {

    private DoublyNode<T> current;
    private final boolean reverse;

    public DoublyIterator(DoublyNode<T> startNode) {
        this(startNode, false);
    }

    public DoublyIterator(DoublyNode<T> startNode, boolean reverse) {
        this.current = startNode;
        this.reverse = reverse;
    }

    public boolean hasNext() {
        return current != null;
    }

    public T next() {
        if (!hasNext()) throw new NoSuchElementException();
        T data = current.data;
        current = reverse ? current.prev : current.next;
        return data;
    }

}
