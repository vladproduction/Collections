package com.vladproduction.ch08_iterator.filtering_and_transform_iterators;

import com.vladproduction.ch08_iterator.EnhancedIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

/**
 * Iterator that filters elements based on a predicate
 */
public class FilteringIterator<T> implements EnhancedIterator<T> {

    private final Iterator<T> baseIterator;
    private final Predicate<T> predicate;
    private T nextItem;
    private boolean hasNextItem = false;
    
    public FilteringIterator(Iterator<T> baseIterator, Predicate<T> predicate) {
        this.baseIterator = baseIterator;
        this.predicate = predicate;
        findNextItem();
    }
    
    private void findNextItem() {
        hasNextItem = false;
        while (baseIterator.hasNext()) {
            T item = baseIterator.next();
            if (predicate.test(item)) {
                nextItem = item;
                hasNextItem = true;
                break;
            }
        }
    }
    
    @Override
    public boolean hasNext() {
        return hasNextItem;
    }
    
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        
        T item = nextItem;
        findNextItem();
        return item;
    }
    
    @Override
    public T peek() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return nextItem;
    }
}

