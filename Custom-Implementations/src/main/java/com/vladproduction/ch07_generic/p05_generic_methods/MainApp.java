package com.vladproduction.ch07_generic.p05_generic_methods;

import com.vladproduction.ch07_generic.p02_pair.Pair;

import java.util.Arrays;

import static com.vladproduction.ch07_generic.p05_generic_methods.GenericMethods.*;


public class MainApp {
    public static void main(String[] args) {

        // Swap example
        String[] names = {"Alice", "Bob", "Charlie"};
        System.out.println("Before swap: " + Arrays.toString(names));
        swap(names, 0, 2);
        System.out.println("After swap: " + Arrays.toString(names));

        // Find max example
        Integer[] numbers = {5, 2, 8, 1, 9};
        Integer max = findMax(numbers);
        System.out.println("Max number: " + max);

        // Create pair example
        Pair<String, Integer> pair = createPair("Count", 5);
        System.out.println("Created pair: " + pair);

        // Sum numbers example
        double sum = sumNumbers(Arrays.asList(1, 2.5, 3.0, 4.5));
        System.out.println("Sum of numbers: " + sum);

    }
}
