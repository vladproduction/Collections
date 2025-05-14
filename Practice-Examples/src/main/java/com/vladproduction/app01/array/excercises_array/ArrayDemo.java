package com.vladproduction.app01.array.excercises_array;

import java.util.Scanner;

public class ArrayDemo {
    public static void main(String[] args) {
        ArrayDemo arrayDemo = new ArrayDemo();
        arrayDemo.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("array.length: ");
        int amount = scanner.nextInt();
        System.out.println("array elements coefficient: ");
        int coefficient = scanner.nextInt();
        showArray(amount, coefficient);
    }

    private int [] showArray(int a, int c) {
        int [] arr = new int[a];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = c*i+1;
            System.out.print("| "+arr[i]+" ");
        }
        return arr;
    }
}
