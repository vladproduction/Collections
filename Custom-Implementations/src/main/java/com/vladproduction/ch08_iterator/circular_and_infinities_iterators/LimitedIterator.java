package com.vladproduction.ch08_iterator.circular_and_infinities_iterators;

import com.vladproduction.ch08_iterator.EnhancedIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator with limited number of elements (for infinite iterators)
 */
public class LimitedIterator<T> implements EnhancedIterator<T> {

    private final Iterator<T> baseIterator;
    private int remaining;

    public LimitedIterator(Iterator<T> baseIterator, int limit) {
        this.baseIterator = baseIterator;
        this.remaining = limit;
    }

    @Override
    public boolean hasNext() {
        return remaining > 0 && baseIterator.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        remaining--;
        return baseIterator.next();
    }

    @Override
    public int getPosition() {
        return remaining;
    }
}
