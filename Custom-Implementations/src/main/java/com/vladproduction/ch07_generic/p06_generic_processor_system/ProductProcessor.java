package com.vladproduction.ch07_generic.p06_generic_processor_system;

/**
 * Processor for product pricing operations
 */
public class ProductProcessor implements Processor<Product> {

    private final double discountPercentage;
    private final double minPrice;

    public ProductProcessor(double discountPercentage, double minPrice) {
        this.discountPercentage = discountPercentage;
        this.minPrice = minPrice;
    }

    @Override
    public Product process(Product input) throws ProcessingException {
        if (input == null) {
            throw new ProcessingException("Product cannot be null");
        }

        double discountedPrice = input.getPrice() * (1 - discountPercentage / 100.0);
        discountedPrice = Math.max(discountedPrice, minPrice);
        discountedPrice = Math.round(discountedPrice * 100.0) / 100.0; // Round to 2 decimals

        return input.withPrice(discountedPrice);
    }

    @Override
    public boolean canProcess(Product input) {
        return input != null
                && input.getPrice() > 0
                && input.getName() != null
                && !input.getName().trim().isEmpty();
    }

    @Override
    public String getDescription() {
        return String.format("Product Processor (%.1f%% discount, min price: %.2f)",
                discountPercentage, minPrice);
    }
}
