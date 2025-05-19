package com.vladproduction.app11.generics.basics;

import java.util.ArrayList;
import java.util.List;

public class GenericsBasicsExamples {
    public static void main(String[] args) {

        /*Generics allow classes, interfaces, and methods to operate on objects
        of various types while providing compile-time type safety. */

        List<String> listGeneric = new ArrayList<>();

        //Without generics:
        List list = new ArrayList(); // Not type-safe
        list.add("abc");
        list.add(123); // No error at compile time

    }
}
