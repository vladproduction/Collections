package com.vladproduction.ch07_generic.p06_generic_processor_system;

import java.util.List;

/**
 * Comprehensive demonstration of the processor system
 */
public class ProcessorDemo {

    public static void main(String[] args) {
        System.out.println("Generic Processor System Demo");
        System.out.println("============================");

        // Single item processing examples
        demonstrateSingleProcessing();

        // Batch processing examples
        demonstrateBatchProcessing();

        // Advanced usage examples
        demonstrateAdvancedUsage();
    }

    private static void demonstrateSingleProcessing() {
        System.out.println("\n" + "#".repeat(60));
        System.out.println("SINGLE ITEM PROCESSING EXAMPLES");
        System.out.println("#".repeat(60));

        // Text processing
        TextProcessor textProcessor = new TextProcessor();
        ProcessorUtils.processAndPrint(textProcessor, "  Hello    WORLD  ");
        ProcessorUtils.processAndPrint(textProcessor, "");
        ProcessorUtils.processAndPrint(textProcessor, null);

        // Number processing
        NumberProcessor squareProcessor = new NumberProcessor(NumberProcessor.MathOperation.SQUARE);
        NumberProcessor roundProcessor = new NumberProcessor(NumberProcessor.MathOperation.ROUND_2_DECIMAL);

        ProcessorUtils.processAndPrint(squareProcessor, 5.0);
        ProcessorUtils.processAndPrint(roundProcessor, 3.14159);
        ProcessorUtils.processAndPrint(squareProcessor, Double.NaN);

        // User processing
        UserProcessor userProcessor = new UserProcessor();
        User user1 = User.builder().name("john DOE").age(25).email("john@example.com").build();
        User user2 = User.builder().name("").age(-5).email("invalid").build();

        ProcessorUtils.processAndPrint(userProcessor, user1);
        ProcessorUtils.processAndPrint(userProcessor, user2);

        // Product processing
        ProductProcessor productProcessor = new ProductProcessor(20.0, 5.0); // 20% discount, min $5
        Product product1 = new Product("Laptop", 1000.0, "Electronics");
        Product product2 = new Product("Cheap Item", 2.0, "Misc");

        ProcessorUtils.processAndPrint(productProcessor, product1);
        ProcessorUtils.processAndPrint(productProcessor, product2);
    }

    private static void demonstrateBatchProcessing() {
        System.out.println("\n" + "#".repeat(60));
        System.out.println("BATCH PROCESSING EXAMPLES");
        System.out.println("#".repeat(60));

        // Batch text processing
        TextProcessor textProcessor = new TextProcessor();
        List<String> texts = List.of(
                "  Hello World  ",
                "Java Programming",
                "",
                "   Generic   Types   ",
                "Design Patterns"
        );
        ProcessorUtils.processBatchAndPrint(textProcessor, texts);

        // Batch number processing
        NumberProcessor sqrtProcessor = new NumberProcessor(NumberProcessor.MathOperation.SQRT);
        List<Double> numbers = List.of(16.0, 25.0, -4.0, 0.0, 100.0, Double.NaN);
        ProcessorUtils.processBatchAndPrint(sqrtProcessor, numbers);

        // Batch user processing
        UserProcessor userProcessor = new UserProcessor();
        List<User> users = List.of(
                User.builder().name("alice johnson").age(30).email("alice@example.com").build(),
                User.builder().name("BOB smith").age(25).email("bob@example.com").build(),
                User.builder().name("").age(20).email("invalid@example.com").build(),
                User.builder().name("charlie brown").age(-5).email("charlie@example.com").build(),
                User.builder().name("diana prince").age(28).email("diana@example.com").build()
        );
        ProcessorUtils.processBatchAndPrint(userProcessor, users);
    }

    private static void demonstrateAdvancedUsage() {
        System.out.println("\n" + "#".repeat(60));
        System.out.println("ADVANCED USAGE EXAMPLES");
        System.out.println("#".repeat(60));

        // Processor chaining (same type)
        NumberProcessor absProcessor = new NumberProcessor(NumberProcessor.MathOperation.ABS);
        NumberProcessor sqrtProcessor = new NumberProcessor(NumberProcessor.MathOperation.SQRT);
        NumberProcessor roundProcessor = new NumberProcessor(NumberProcessor.MathOperation.ROUND_2_DECIMAL);

        ProcessorUtils.processChain(-16.0, absProcessor, sqrtProcessor, roundProcessor);

        // Custom processor creation using lambda
        Processor<String> customStringProcessor = new Processor<String>() {
            @Override
            public String process(String input) throws ProcessingException {
                return input.toUpperCase().replace(" ", "_");
            }

            @Override
            public boolean canProcess(String input) {
                return input != null && input.length() > 2;
            }

            @Override
            public String getDescription() {
                return "Custom String Processor (uppercase + underscore)";
            }
        };

        ProcessorUtils.processAndPrint(customStringProcessor, "hello world");

        // Demonstration of processor composition
        demonstrateProcessorComposition();
    }

    private static void demonstrateProcessorComposition() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("PROCESSOR COMPOSITION EXAMPLE");
        System.out.println("Creating a composite processor for complex workflows");

        // Create a composite processor that combines multiple operations
        class CompositeUserProcessor implements Processor<User> {
            private final UserProcessor baseProcessor = new UserProcessor();

            @Override
            public User process(User input) throws ProcessingException {
                // First apply base processing
                User processed = baseProcessor.process(input);

                // Then apply additional business logic
                if (processed.getAge() < 18) {
                    throw new ProcessingException("User must be 18 or older");
                }

                return processed;
            }

            @Override
            public boolean canProcess(User input) {
                return baseProcessor.canProcess(input);
            }

            @Override
            public String getDescription() {
                return "Composite User Processor (normalization + age validation)";
            }
        }

        CompositeUserProcessor compositeProcessor = new CompositeUserProcessor();

        User adult = User.builder().name("john doe").age(25).email("john@example.com").build();
        User minor = User.builder().name("jane doe").age(16).email("jane@example.com").build();

        ProcessorUtils.processAndPrint(compositeProcessor, adult);
        ProcessorUtils.processAndPrint(compositeProcessor, minor);
    }
}
