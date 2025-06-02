package com.vladproduction.ch07_generic.p06_generic_processor_system;

/**
 * Product class for e-commerce scenarios
 */
public class Product {

    private final String name;
    private final double price;
    private final String category;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public Product withPrice(double newPrice) {
        return new Product(this.name, newPrice, this.category);
    }

    @Override
    public String toString() {
        return String.format("Product{name='%s', price=%.2f, category='%s'}",
                name, price, category);
    }
}
