package com.vladproduction.ch07_generic.p06_generic_processor_system;

import java.util.List;

/**
 * Utility class for processor operations
 */
public class ProcessorUtils {

    /**
     * Process and print results with detailed information
     */
    public static <T> void processAndPrint(Processor<T> processor, T input) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Processor: " + processor.getDescription());
        System.out.println("Input: " + input);

        try {
            if (processor.canProcess(input)) {
                T result = processor.process(input);
                System.out.println("Output: " + result);
                System.out.println("Status: SUCCESS");
            } else {
                System.out.println("Status: SKIPPED (validation failed)");
            }
        } catch (ProcessingException e) {
            System.out.println("Status: ERROR - " + e.getMessage());
        }
    }

    /**
     * Process a batch of items and print summary
     */
    public static <T> void processBatchAndPrint(Processor<T> processor, List<T> inputs) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("BATCH PROCESSING");
        System.out.println("Processor: " + processor.getDescription());
        System.out.println("Input count: " + inputs.size());

        ProcessingResult<T> result = processor.processAll(inputs);

        System.out.println("Results: " + result);
        System.out.println("Processed items:");
        result.getProcessedItems().forEach(item -> System.out.println("  - " + item));
    }

    /**
     * Chain multiple processors together
     */
    @SafeVarargs
    public static <T> void processChain(T input, Processor<T>... processors) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("PROCESSING CHAIN");
        T current = input;
        System.out.println("Initial input: " + current);

        for (int i = 0; i < processors.length; i++) {
            Processor<T> processor = processors[i];
            System.out.println("\nStep " + (i + 1) + ": " + processor.getDescription());

            try {
                if (processor.canProcess(current)) {
                    current = processor.process(current);
                    System.out.println("Result: " + current);
                } else {
                    System.out.println("Chain broken: validation failed");
                    return;
                }
            } catch (ProcessingException e) {
                System.out.println("Chain broken: " + e.getMessage());
                return;
            }
        }

        System.out.println("\nFinal result: " + current);
    }
}
