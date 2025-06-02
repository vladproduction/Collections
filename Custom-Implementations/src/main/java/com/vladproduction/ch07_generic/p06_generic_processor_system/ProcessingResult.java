package com.vladproduction.ch07_generic.p06_generic_processor_system;

import java.util.List;

/**
 * Result of batch processing operation
 */
public class ProcessingResult<T> {

    private final List<T> processedItems;
    private final int skippedCount;

    public ProcessingResult(List<T> processedItems, int skippedCount) {
        this.processedItems = processedItems;
        this.skippedCount = skippedCount;
    }

    public List<T> getProcessedItems() {
        return processedItems;
    }

    public int getSkippedCount() {
        return skippedCount;
    }

    public int getProcessedCount() {
        return processedItems.size();
    }

    @Override
    public String toString() {
        return String.format("ProcessingResult{processed=%d, skipped=%d}",
                getProcessedCount(), skippedCount);
    }
}
