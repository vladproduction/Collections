package com.vladproduction.ch08_iterator.binary_tree_traversal_iterators;

import com.vladproduction.ch08_iterator.EnhancedIterator;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Post-order traversal iterator (Left -> Right -> Root)
 */
public class PostOrderIterator<T> implements EnhancedIterator<T> {

    private final Stack<TreeNode<T>> stack = new Stack<>();
    private TreeNode<T> lastVisited = null;

    public PostOrderIterator(TreeNode<T> root) {
        pushLeft(root);
    }

    private void pushLeft(TreeNode<T> node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        TreeNode<T> current = stack.peek();

        // If right child exists and hasn't been processed
        if (current.right != null && current.right != lastVisited) {
            pushLeft(current.right);
            return next(); // Recursive call
        } else {
            // Process current node
            stack.pop();
            lastVisited = current;
            return current.data;
        }
    }
}
