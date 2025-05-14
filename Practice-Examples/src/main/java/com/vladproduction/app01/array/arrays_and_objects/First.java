package com.vladproduction.app01.array.arrays_and_objects;

public class First {
    int[] numbers;

    public First(int n) {
        numbers = new int[n];
        numbers[0] = 1;
        numbers[1] = 1;
        //Fibonacci row:
        for (int i = 2; i < n; i++) {
            numbers[i] = numbers[i - 1] + numbers[i - 2];
        }
    }

    void show() {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
    }
}

