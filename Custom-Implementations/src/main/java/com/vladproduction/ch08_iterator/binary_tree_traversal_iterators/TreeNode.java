package com.vladproduction.ch08_iterator.binary_tree_traversal_iterators;

/**
 * Generic tree node for binary tree implementations
 */
public class TreeNode<T> {
    T data;
    TreeNode<T> left;
    TreeNode<T> right;
    TreeNode<T> parent; // For certain traversal algorithms
    
    public TreeNode(T data) {
        this.data = data;
    }
    
    public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
        if (left != null) left.parent = this;
        if (right != null) right.parent = this;
    }
    
    @Override
    public String toString() {
        return String.valueOf(data);
    }
}