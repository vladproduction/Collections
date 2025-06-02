package com.vladproduction.ch08_iterator.circular_and_infinities_iterators;

import com.vladproduction.ch08_iterator.EnhancedIterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Iterator that cycles through a collection infinitely
 */
public class CircularIterator<T> implements EnhancedIterator<T> {

    private final List<T> items;
    private int currentIndex = 0;
    private int cycleCount = 0;
    
    public CircularIterator(Collection<T> items) {
        this.items = new ArrayList<>(items);
        if (this.items.isEmpty()) {
            throw new IllegalArgumentException("Cannot create circular iterator with empty collection");
        }
    }
    
    @Override
    public boolean hasNext() {
        return true; // Always has next in circular iterator
    }
    
    @Override
    public T next() {
        T item = items.get(currentIndex);
        currentIndex = (currentIndex + 1) % items.size();
        if (currentIndex == 0) {
            cycleCount++;
        }
        return item;
    }
    
    @Override
    public int getPosition() {
        return currentIndex;
    }
    
    public int getCycleCount() {
        return cycleCount;
    }
    
    @Override
    public void reset() {
        currentIndex = 0;
        cycleCount = 0;
    }
}

