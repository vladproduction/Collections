package com.vladproduction.app11.generics.basics;

import java.util.List;

public class BoundedLower {

    public static void main(String[] args) {
        List<Number> listIntegers = List.of(1, 2, 3, 4, 5);
        List<Number> listDoubles = List.of(1.0, 2.0, 3.0, 4.0, 5.0);
        List<Number> listFloats = List.of(1f, 2f, 3f, 4f, 5f);
        List<Number> listLongs = List.of(1L, 2L, 3L, 4L, 5L);

        ProperElementList.processListOfNumbers(listIntegers);
        ProperElementList.processListOfNumbers(listDoubles);
        ProperElementList.processListOfNumbers(listFloats);
        ProperElementList.processListOfNumbers(listLongs);
    }


    static class ProperElementList {

        private static void processListOfNumbers(List<? super Number> list) {
            for (Object o : list) {
                System.out.print(o + " ");
            }
            System.out.println();
        }
    }
}
