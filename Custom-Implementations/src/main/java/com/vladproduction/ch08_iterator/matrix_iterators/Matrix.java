package com.vladproduction.ch08_iterator.matrix_iterators;

import com.vladproduction.ch08_iterator.EnhancedIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 2D Matrix with multiple traversal patterns
 */
public class Matrix<T> implements Iterable<T> {
    private final T[][] matrix;
    private final int rows;
    private final int cols;
    
    public enum TraversalPattern {
        ROW_MAJOR, COLUMN_MAJOR, DIAGONAL, SPIRAL, ZIGZAG
    }
    
    @SuppressWarnings("unchecked")
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = (T[][]) new Object[rows][cols];
    }
    
    public void set(int row, int col, T value) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            matrix[row][col] = value;
        }
    }
    
    public T get(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return matrix[row][col];
        }
        return null;
    }
    
    @Override
    public Iterator<T> iterator() {
        return new RowMajorIterator();
    }
    
    public Iterator<T> getIterator(TraversalPattern pattern) {

        switch (pattern) {
            case ROW_MAJOR: return new RowMajorIterator();
            case COLUMN_MAJOR: return new ColumnMajorIterator();
            case DIAGONAL: return new DiagonalIterator();
            case SPIRAL: return new SpiralIterator();
            case ZIGZAG: return new ZigZagIterator();
            default: return new RowMajorIterator();
        }

    }
    
    /**
     * Row-major order iterator (left to right, top to bottom)
     */
    private class RowMajorIterator implements EnhancedIterator<T> {

        private int currentRow = 0;
        private int currentCol = 0;
        
        @Override
        public boolean hasNext() {
            return currentRow < rows;
        }
        
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            
            T value = matrix[currentRow][currentCol];
            
            currentCol++;
            if (currentCol >= cols) {
                currentCol = 0;
                currentRow++;
            }
            
            return value;
        }
        
        @Override
        public int getPosition() {
            return currentRow * cols + currentCol;
        }
    }
    
    /**
     * Column-major order iterator (top to bottom, left to right)
     */
    private class ColumnMajorIterator implements EnhancedIterator<T> {

        private int currentRow = 0;
        private int currentCol = 0;
        
        @Override
        public boolean hasNext() {
            return currentCol < cols;
        }
        
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            
            T value = matrix[currentRow][currentCol];
            
            currentRow++;
            if (currentRow >= rows) {
                currentRow = 0;
                currentCol++;
            }
            
            return value;
        }
    }
    
    /**
     * Diagonal traversal iterator
     */
    private class DiagonalIterator implements EnhancedIterator<T> {

        private int currentDiagonal = 0;
        private int positionInDiagonal = 0;
        private final int totalDiagonals = rows + cols - 1;
        
        @Override
        public boolean hasNext() {
            return currentDiagonal < totalDiagonals;
        }
        
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            
            int row, col;
            
            if (currentDiagonal < rows) {
                row = currentDiagonal - positionInDiagonal;
                col = positionInDiagonal;
            } else {
                row = rows - 1 - positionInDiagonal;
                col = currentDiagonal - rows + 1 + positionInDiagonal;
            }
            
            T value = matrix[row][col];
            
            positionInDiagonal++;
            int maxPositions = Math.min(currentDiagonal + 1, Math.min(rows, cols));
            if (currentDiagonal >= rows) {
                maxPositions = rows + cols - 1 - currentDiagonal;
            }
            
            if (positionInDiagonal >= maxPositions) {
                positionInDiagonal = 0;
                currentDiagonal++;
            }
            
            return value;
        }
    }
    
    /**
     * Spiral traversal iterator (clockwise from outside to inside)
     */
    private class SpiralIterator implements EnhancedIterator<T> {

        private int top = 0, bottom = rows - 1;
        private int left = 0, right = cols - 1;
        private int currentRow = 0, currentCol = 0;
        private Direction direction = Direction.RIGHT;
        private int elementsVisited = 0;
        
        private enum Direction {
            RIGHT,
            DOWN,
            LEFT,
            UP
        }
        
        @Override
        public boolean hasNext() {
            return elementsVisited < rows * cols;
        }
        
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            
            T value = matrix[currentRow][currentCol];
            elementsVisited++;
            
            switch (direction) {
                case RIGHT:
                    if (currentCol == right) {
                        direction = Direction.DOWN;
                        top++;
                        currentRow++;
                    } else {
                        currentCol++;
                    }
                    break;
                case DOWN:
                    if (currentRow == bottom) {
                        direction = Direction.LEFT;
                        right--;
                        currentCol--;
                    } else {
                        currentRow++;
                    }
                    break;
                case LEFT:
                    if (currentCol == left) {
                        direction = Direction.UP;
                        bottom--;
                        currentRow--;
                    } else {
                        currentCol--;
                    }
                    break;
                case UP:
                    if (currentRow == top) {
                        direction = Direction.RIGHT;
                        left++;
                        currentCol++;
                    } else {
                        currentRow--;
                    }
                    break;
            }
            
            return value;
        }
    }
    
    /**
     * ZigZag traversal iterator (alternating left-right, right-left)
     */
    private class ZigZagIterator implements EnhancedIterator<T> {

        private int currentRow = 0;
        private int currentCol = 0;
        private boolean leftToRight = true;
        
        @Override
        public boolean hasNext() {
            return currentRow < rows;
        }
        
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            
            T value = matrix[currentRow][currentCol];
            
            if (leftToRight) {
                currentCol++;
                if (currentCol >= cols) {
                    leftToRight = false;
                    currentRow++;
                    currentCol = cols - 1;
                }
            } else {
                currentCol--;
                if (currentCol < 0) {
                    leftToRight = true;
                    currentRow++;
                    currentCol = 0;
                }
            }
            
            return value;
        }
    }
}