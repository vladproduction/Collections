package com.vladproduction.ch03_linkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MyLinkedListImpl<T> implements MyLinkedList<T>{

    private Node<T> root;                       //root element(first node)
    private int size;
    private int modCount;                       //for fail-fast safety

    public MyLinkedListImpl(){
        root = null;
        size = 0;
        modCount = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void add(T item) {
        if(item == null){
            throw new IllegalArgumentException("Can not add null to the list");
        }

        Node<T> newNode = new Node<>(item);
        if(root == null){
            root = newNode;
        } else {
            Node<T> temp = root;
            while (temp.getNext() != null){
                temp = temp.getNext();
            }
            temp.setNext(newNode);
        }

        size++;
        modCount++;
    }

    @Override
    public T getByIndex(int index) {
        checkIndex(index);

        Node<T> temp = root;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }

        return temp.getValue();
    }

    @Override
    public T setByIndex(int index, T newItem) {
        if (newItem == null) {
            throw new IllegalArgumentException("Cannot set null item to the list");
        }

        checkIndex(index);
        Node<T> temp = root;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }

        T oldValue = temp.getValue();
        temp.setValue(newItem);
        modCount++;
        return oldValue;
    }

    @Override
    public boolean contains(T item) {
        if (item == null) return false;

        Node<T> temp = root;
        while (temp != null){
            if(temp.getValue().equals(item)){
                return true;
            }
            temp = temp.getNext();
        }

        return false;
    }

    @Override
    public void clear() {
        Node<T> current = root;
        while(current != null){
            Node<T> next = current.getNext();
            current.setNext(null);
            current.setValue(null);
            current = next;
        }

        root = null;
        size = 0;
        modCount++;
    }

    @Override
    public void removeByIndex(int index) {
        checkIndex(index);

        // Special case: removing the first element
        if (index == 0) {
            root = root.getNext();
            size--;
            modCount++;
            return;
        }

        // Find the node before the one to remove
        Node<T> temp = root;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.getNext();
        }

        Node<T> toDelete = temp.getNext();
        temp.setNext(toDelete.getNext());
        size--;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    @Override
    public boolean equals(Object o) {

        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyLinkedListImpl<?> that = (MyLinkedListImpl<?>) o;
        if(this.size != that.size) return false;

        Node<T> currentThis = this.root;
        Node<?> currentThat = that.root;

        while (currentThis != null && currentThat != null){

            if(!Objects.equals(currentThis.getValue(), currentThat.getValue())){
                return false;
            }
            currentThis = currentThis.getNext();
            currentThat = currentThat.getNext();
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        Node<T> current = root;
        while (current != null) {
            result = 31 * result + Objects.hashCode(current.getValue());
            current = current.getNext();
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[");

        Node<T> current = root;
        while (current != null){
            sb.append(current.getValue());
            if(current.getNext() != null){
                sb.append(", ");
            }
            current = current.getNext();
        }

        sb.append("]");
        return sb.toString();
    }

    /**
     * helpers methods
     * */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    //useful method for debugging
    public Node<T> getRoot() {
        return root;
    }

    /**
     * My custom iterator implementation class
     * - iteration;
     * - proper removing;
     * - check for concurrent modification;
     */
    private class MyLinkedListIterator implements Iterator<T>{

        private Node<T> current = root;
        private Node<T> lastReturned;
        private int expectedModCount = modCount;
        private boolean canRemove = false;

        @Override
        public boolean hasNext() {
            checkForModification();
            return current != null;
        }

        @Override
        public T next() {
            checkForModification();
            if (current == null) {
                throw new NoSuchElementException();
            }

            canRemove = true;
            lastReturned = current;
            T value = current.getValue();
            current = current.getNext();
            return value;
        }

        @Override
        public void remove() {
            checkForModification();
            if (!canRemove) {
                throw new IllegalStateException("next() must be called before remove()");
            }

            if (lastReturned == root) {
                root = root.getNext();
            } else {
                Node<T> previous = root;
                while (previous != null && previous.getNext() != lastReturned) {
                    previous = previous.getNext();
                }
                if (previous != null) {
                    previous.setNext(lastReturned.getNext());
                }
            }

            lastReturned = null;
            canRemove = false;
            size--;
            modCount++;
            expectedModCount++;
        }

        private void checkForModification(){
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("List was modified during iteration");
            }
        }

    }

    /**
     * Node class defined as static and managing references of the next Node objects;
     * */
    public static class Node<T>{

        private Node<T> next;
        private T value;

        public Node(){
        }

        public Node(T value){
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{value=" + value + "}";
        }
    }

}
