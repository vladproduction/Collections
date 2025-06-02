package com.vladproduction.ch08_iterator.binary_tree_traversal_iterators;

import com.vladproduction.ch08_iterator.EnhancedIterator;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * In-order traversal iterator (Left -> Root -> Right)
 */
public class InOrderIterator<T> implements EnhancedIterator<T> {

    private final Stack<TreeNode<T>> stack = new Stack<>();
    private TreeNode<T> current;

    public InOrderIterator(TreeNode<T> root) {
        current = root;
    }

    @Override
    public boolean hasNext() {
        return current != null || !stack.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        // Go to leftmost node
        while (current != null) {
            stack.push(current);
            current = current.left;
        }

        // Pop and process current node
        current = stack.pop();
        T data = current.data;

        // Move to right subtree
        current = current.right;

        return data;
    }

    @Override
    public T peek() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        TreeNode<T> tempCurrent = current;
        Stack<TreeNode<T>> tempStack = new Stack<>();
        tempStack.addAll(stack);

        while (tempCurrent != null) {
            tempStack.push(tempCurrent);
            tempCurrent = tempCurrent.left;
        }

        return tempStack.peek().data;
    }
}

