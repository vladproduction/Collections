package com.vladproduction.app11.generics.basics;

public class GenericMethodExample {

    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3};
        String[] stringArray = { "Hello", "World" };
        Person[] personArray = {new Person("John", "Doe"), new Person("Jane", "Doe"), new Person("Jack", "Doe")};

        UtilityClass.printArray(intArray);
        UtilityClass.printArray(stringArray);
        UtilityClass.printArray(personArray);
    }

    static class UtilityClass {

        public static <T> void printArray(T[] array) {
            System.out.println("Printing array of T class as: " + array.getClass().getSimpleName());
            for (T element : array) {
                System.out.print(element + " ");
            }
            System.out.println();
        }

    }

    record Person(String firstName, String lastName) {
        @Override
        public String toString() {
            return String.format("[%s %s]", firstName, lastName);
        }
    }
}
