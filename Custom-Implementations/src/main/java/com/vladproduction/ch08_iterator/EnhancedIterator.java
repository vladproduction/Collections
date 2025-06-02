package com.vladproduction.ch08_iterator;

import java.util.Iterator;

/**
 * Enhanced iterator interface with additional capabilities
 */
public interface EnhancedIterator<T> extends Iterator<T> {
    /**
     * Peek at the next element without consuming it
     */
    default T peek() {
        throw new UnsupportedOperationException("Peek not supported");
    }
    
    /**
     * Get the current position/index if applicable
     */
    default int getPosition() {
        throw new UnsupportedOperationException("Position tracking not supported");
    }
    
    /**
     * Reset iterator to beginning if supported
     */
    default void reset() {
        throw new UnsupportedOperationException("Reset not supported");
    }
}