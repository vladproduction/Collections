package com.vladproduction.ch08_iterator.binary_tree_traversal_iterators;

import java.util.Iterator;

/**
 * Binary tree with multiple traversal iterators
 */
public class BinaryTree<T> implements Iterable<T> {

    private TreeNode<T> root;
    private TraversalType defaultTraversal = TraversalType.IN_ORDER;

    public enum TraversalType {
        IN_ORDER,
        PRE_ORDER,
        POST_ORDER,
        LEVEL_ORDER
    }

    public BinaryTree() {
    }

    public BinaryTree(TreeNode<T> root) {
        this.root = root;
    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void setDefaultTraversal(TraversalType traversal) {
        this.defaultTraversal = traversal;
    }

    @Override
    public Iterator<T> iterator() {
        return getIterator(defaultTraversal);
    }

    public Iterator<T> getIterator(TraversalType type) {
        switch (type) {
            case IN_ORDER:
                return new InOrderIterator<>(root);
            case PRE_ORDER:
                return new PreOrderIterator<>(root);
            case POST_ORDER:
                return new PostOrderIterator<>(root);
            case LEVEL_ORDER:
                return new LevelOrderIterator<>(root);
            default:
                return new InOrderIterator<>(root);
        }
    }
}