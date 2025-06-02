package com.vladproduction.ch08_iterator.filtering_and_transform_iterators;

import java.util.Iterator;
import java.util.function.Function;

/**
 * Iterator that transforms elements using a function
 */
public class TransformingIterator<T, R> implements Iterator<R> {

    private final Iterator<T> baseIterator;
    private final Function<T, R> transformer;

    public TransformingIterator(Iterator<T> baseIterator, Function<T, R> transformer) {
        this.baseIterator = baseIterator;
        this.transformer = transformer;
    }

    @Override
    public boolean hasNext() {
        return baseIterator.hasNext();
    }

    @Override
    public R next() {
        return transformer.apply(baseIterator.next());
    }
}
