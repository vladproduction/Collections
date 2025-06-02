package com.vladproduction.ch08_iterator;

import com.vladproduction.ch08_iterator.circular_and_infinities_iterators.CircularIterator;
import com.vladproduction.ch08_iterator.circular_and_infinities_iterators.LimitedIterator;
import com.vladproduction.ch08_iterator.filtering_and_transform_iterators.ChainedIterator;
import com.vladproduction.ch08_iterator.filtering_and_transform_iterators.FilteringIterator;
import com.vladproduction.ch08_iterator.filtering_and_transform_iterators.TransformingIterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Utility class for creating and working with custom iterators
 */
public class IteratorUtils {
    
    /**
     * Create a filtering iterator
     */
    public static <T> Iterator<T> filter(Iterator<T> iterator, Predicate<T> predicate) {
        return new FilteringIterator<>(iterator, predicate);
    }
    
    /**
     * Create a transforming iterator
     */
    public static <T, R> Iterator<R> map(Iterator<T> iterator, Function<T, R> transformer) {
        return new TransformingIterator<>(iterator, transformer);
    }
    
    /**
     * Chain multiple iterators
     */
    @SafeVarargs
    public static <T> Iterator<T> chain(Iterator<T>... iterators) {
        return new ChainedIterator<>(iterators);
    }
    
    /**
     * Limit the number of elements from an iterator
     */
    public static <T> Iterator<T> limit(Iterator<T> iterator, int maxElements) {
        return new LimitedIterator<>(iterator, maxElements);
    }
    
    /**
     * Create a circular iterator from a collection
     */
    public static <T> CircularIterator<T> cycle(Collection<T> items) {
        return new CircularIterator<>(items);
    }
    
    /**
     * Convert iterator to list
     */
    public static <T> List<T> toList(Iterator<T> iterator) {
        List<T> result = new ArrayList<>();
        iterator.forEachRemaining(result::add);
        return result;
    }
    
    /**
     * Print all elements from an iterator
     */
    public static <T> void printAll(Iterator<T> iterator, String description) {
        System.out.println("\n" + description + ":");
        List<T> items = new ArrayList<>();
        iterator.forEachRemaining(items::add);
        
        if (items.isEmpty()) {
            System.out.println("  (empty)");
        } else {
            items.forEach(item -> System.out.print(item + " "));
            System.out.println();
        }
    }
}