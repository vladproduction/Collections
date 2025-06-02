package com.vladproduction.ch07_generic.p06_generic_processor_system;

import java.util.function.Function;

/**
 * Processor for mathematical operations on numbers
 */
public class NumberProcessor implements Processor<Double> {

    private final MathOperation operation;

    public enum MathOperation {
        SQUARE(x -> x * x, "Square"),
        SQRT(Math::sqrt, "Square Root"),
        ROUND_2_DECIMAL(x -> Math.round(x * 100.0) / 100.0, "Round to 2 decimals"),
        ABS(Math::abs, "Absolute Value");

        private final Function<Double, Double> function;
        private final String description;

        MathOperation(Function<Double, Double> function, String description) {
            this.function = function;
            this.description = description;
        }

        public Double apply(Double value) {
            return function.apply(value);
        }

        public String getDescription() {
            return description;
        }
    }

    public NumberProcessor(MathOperation operation) {
        this.operation = operation;
    }

    @Override
    public Double process(Double input) throws ProcessingException {
        if (input == null) {
            throw new ProcessingException("Input cannot be null");
        }

        Double result = operation.apply(input);

        if (result.isNaN() || result.isInfinite()) {
            throw new ProcessingException("Mathematical operation resulted in invalid number");
        }

        return result;
    }

    @Override
    public boolean canProcess(Double input) {
        return input != null && !input.isNaN() && !input.isInfinite();
    }

    @Override
    public String getDescription() {
        return "Number Processor (" + operation.getDescription() + ")";
    }
}
