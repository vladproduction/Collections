package com.vladproduction.ch07_generic.p05_generic_methods;

import com.vladproduction.ch07_generic.p02_pair.Pair;

import java.util.List;

public class GenericMethods {

    /**
     * generic method to swap two elements in an array
     * */
    public static <T> void swap(T[] array, int i, int j){
        if (i >= 0 && j >= 0 && i < array.length && j < array.length) {
            T temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    /**
     * generic method to find maximum value in an array
     * */
    public static <T extends Comparable<T>> T findMax(T[] array){
        if (array == null || array.length == 0) {
            return null;
        }

        T max = array[0];
        for (int i = 1; i < array.length; i++) {
            if(array[i].compareTo(max) > 0){
                max = array[i];
            }
        }
        return max;
    }

    /**
     * generic method with multiple type parameters
     * */
    public static <T, U> Pair<T, U> createPair(T first, U second){
        return new Pair<>(first, second);
    }

    /**
     * generic method with wildcard type parameter
     * */
    public static double sumNumbers(List<? extends Number> numbers){
        return numbers.stream()
                .mapToDouble(Number::doubleValue)
                .sum();
    }


}

















