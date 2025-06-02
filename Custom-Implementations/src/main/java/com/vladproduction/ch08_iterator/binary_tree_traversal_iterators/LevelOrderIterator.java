package com.vladproduction.ch08_iterator.binary_tree_traversal_iterators;

import com.vladproduction.ch08_iterator.EnhancedIterator;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Level-order traversal iterator (breadth-first)
 */
public class LevelOrderIterator<T> implements EnhancedIterator<T> {

    private final Queue<TreeNode<T>> queue = new LinkedList<>();

    public LevelOrderIterator(TreeNode<T> root) {
        if (root != null) {
            queue.offer(root);
        }
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        TreeNode<T> current = queue.poll();

        if (current.left != null) {
            queue.offer(current.left);
        }
        if (current.right != null) {
            queue.offer(current.right);
        }

        return current.data;
    }

    @Override
    public T peek() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return queue.peek().data;
    }
}
