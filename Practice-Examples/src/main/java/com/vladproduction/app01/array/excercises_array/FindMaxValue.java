package com.vladproduction.app01.array.excercises_array;

import java.util.Scanner;

public class FindMaxValue {
    public static void main(String[] args) {
        FindMaxValue findMaxValue = new FindMaxValue();
        findMaxValue.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Set array:");
        String [] numbs = scanner.nextLine().split(" ");

        System.out.println("Max value:");
        findMax(numbs);

    }

    private void findMax(String [] numbs) {
        int[] array = new int[numbs.length];
        for (int i = 0; i < numbs.length; i++) {
            array[i] = Integer.parseInt(numbs[i]);
        }
        int value = array[0];
        for (int i = 1; i < array.length; i++) {
            if(array[i]>value){
                value = array[i];
            }
        }
        System.out.println(value);
    }
}
