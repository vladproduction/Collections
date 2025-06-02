package com.vladproduction.ch07_generic.p06_generic_processor_system;

import java.util.stream.Collectors;

/**
 * Processor for user data validation and transformation
 */
public class UserProcessor implements Processor<User> {

    @Override
    public User process(User input) throws ProcessingException {
        if (input == null) {
            throw new ProcessingException("User cannot be null");
        }

        // Normalize name (capitalize first letter of each word)
        String normalizedName = normalizeUserName(input.getName());

        return input.withName(normalizedName);
    }

    @Override
    public boolean canProcess(User input) {
        return input != null
            && input.getName() != null
            && !input.getName().trim().isEmpty()
            && input.getAge() > 0
            && input.getAge() < 150;
    }

    @Override
    public String getDescription() {
        return "User Data Processor (name normalization, validation)";
    }

    private String normalizeUserName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return name;
        }

        return java.util.Arrays.stream(name.trim().toLowerCase().split("\\s+"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining(" "));
    }
}
