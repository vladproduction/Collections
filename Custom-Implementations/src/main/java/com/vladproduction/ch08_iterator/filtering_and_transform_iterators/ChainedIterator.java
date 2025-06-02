package com.vladproduction.ch08_iterator.filtering_and_transform_iterators;

import com.vladproduction.ch08_iterator.EnhancedIterator;

import java.util.*;

/**
 * Iterator that combines multiple iterators in sequence
 */
public class ChainedIterator<T> implements EnhancedIterator<T> {

    private final List<Iterator<T>> iterators;
    private int currentIteratorIndex = 0;

    @SafeVarargs
    public ChainedIterator(Iterator<T>... iterators) {
        this.iterators = Arrays.asList(iterators);
    }

    public ChainedIterator(List<Iterator<T>> iterators) {
        this.iterators = new ArrayList<>(iterators);
    }

    @Override
    public boolean hasNext() {
        while (currentIteratorIndex < iterators.size()) {
            if (iterators.get(currentIteratorIndex).hasNext()) {
                return true;
            }
            currentIteratorIndex++;
        }
        return false;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return iterators.get(currentIteratorIndex).next();
    }

    @Override
    public int getPosition() {
        return currentIteratorIndex;
    }
}
