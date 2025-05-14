package com.vladproduction.app01.array.excercises_array;

import java.util.Random;
import java.util.Scanner;

public class ArrayRandNumbs {
    public static void main(String[] args) {
        ArrayRandNumbs arrayRandNumbs = new ArrayRandNumbs();
        arrayRandNumbs.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Set array length:");
        int n = scanner.nextInt();
        int [] array = new int[n];
        showArray(array);
    }

    private int [] showArray(int [] arr) {
        int [] array = new int[arr.length];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(21)+1;
            System.out.print(array[i] + " ");
        }
        System.out.println();
        // show array revert:
        for (int i = array.length-1; i >=0; i--) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        return array;
    }
}
