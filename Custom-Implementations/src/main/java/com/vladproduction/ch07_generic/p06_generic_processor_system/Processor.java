package com.vladproduction.ch07_generic.p06_generic_processor_system;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Generic processor interface for transforming and validating data of type T
 * @param <T> the type of data to process
 */
public interface Processor<T> {

    /**
     * Process a single input item
     * @param input the item to process
     * @return the processed item
     * @throws ProcessingException if processing fails
     */
    T process(T input) throws ProcessingException;

    /**
     * Check if the input can be processed
     * @param input the item to validate
     * @return true if the item can be processed
     */
    boolean canProcess(T input);

    /**
     * Get a description of what this processor does
     * @return processor description
     */
    default String getDescription() {
        return this.getClass().getSimpleName();
    }

    /**
     * Process a list of items, filtering out invalid ones
     * @param inputs list of items to process
     * @return list of successfully processed items
     */
    default ProcessingResult<T> processAll(List<T> inputs) {
        List<T> processed = inputs.stream()
                .filter(this::canProcess)
                .map(item -> {
                    try {
                        return process(item);
                    } catch (ProcessingException e) {
                        return null; // Will be filtered out
                    }
                })
                .filter(item -> item != null)
                .collect(Collectors.toList());

        int skipped = inputs.size() - processed.size();
        return new ProcessingResult<>(processed, skipped);
    }
}
