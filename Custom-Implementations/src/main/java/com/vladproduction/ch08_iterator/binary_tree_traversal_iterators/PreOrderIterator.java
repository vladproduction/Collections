package com.vladproduction.ch08_iterator.binary_tree_traversal_iterators;

import com.vladproduction.ch08_iterator.EnhancedIterator;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Pre-order traversal iterator (Root -> Left -> Right)
 */
class PreOrderIterator<T> implements EnhancedIterator<T> {

    private final Stack<TreeNode<T>> stack = new Stack<>();

    public PreOrderIterator(TreeNode<T> root) {
        if (root != null) {
            stack.push(root);
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

        TreeNode<T> current = stack.pop();

        // Push right first, then left (so left is processed first)
        if (current.right != null) {
            stack.push(current.right);
        }
        if (current.left != null) {
            stack.push(current.left);
        }

        return current.data;
    }

    @Override
    public T peek() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return stack.peek().data;
    }
}
