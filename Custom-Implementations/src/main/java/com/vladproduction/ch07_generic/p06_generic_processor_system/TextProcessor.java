package com.vladproduction.ch07_generic.p06_generic_processor_system;

/**
 * Processor for text normalization and validation
 */
public class TextProcessor implements Processor<String> {

    @Override
    public String process(String input) throws ProcessingException {
        if (input == null) {
            throw new ProcessingException("Input cannot be null");
        }

        String processed = input.trim()
                .replaceAll("\\s+", " ") // normalize whitespace
                .toLowerCase();

        if (processed.isEmpty()) {
            throw new ProcessingException("Input cannot be empty after processing");
        }

        return processed;
    }

    @Override
    public boolean canProcess(String input) {
        return input != null && !input.trim().isEmpty();
    }

    @Override
    public String getDescription() {
        return "Text Normalizer (trim, normalize whitespace, lowercase)";
    }
}
