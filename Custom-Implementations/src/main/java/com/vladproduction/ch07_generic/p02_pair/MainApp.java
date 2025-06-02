package com.vladproduction.ch07_generic.p02_pair;

public class MainApp {
    public static void main(String[] args) {

        // Create pairs
        Pair<String, Integer> nameAge = new Pair<>("Alice", 25);
        Pair<Double, Boolean> priceAvailable = new Pair<>(19.99, true);

        //toString()
        System.out.println("Name-Age pair: " + nameAge);
        System.out.println("Price-Available pair: " + priceAvailable);

        // Get values
        System.out.println("Name: " + nameAge.getFirst());
        System.out.println("Age: " + nameAge.getSecond());
        System.out.println("Price: " + priceAvailable.getFirst());
        System.out.println("Available: " + priceAvailable.getSecond());

    }
}
