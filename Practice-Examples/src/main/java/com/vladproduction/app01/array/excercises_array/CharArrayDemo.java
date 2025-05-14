package com.vladproduction.app01.array.excercises_array;

import java.util.Random;
import java.util.Scanner;

public class CharArrayDemo {
    public static void main(String[] args) {
        CharArrayDemo arrayRandNumbs = new CharArrayDemo();
        arrayRandNumbs.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Set array length:");
        int n = scanner.nextInt();
        char [] a = new char [n];
        showArray(a);
    }

    private char [] showArray(char [] arr) {
        char [] array = new char[arr.length];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = (char) ('a'+random.nextInt(28));
            System.out.print("| "+array[i] + " ");
        }
        System.out.println("|");
        // array of random length:
        char [] b = new char[random.nextInt(5)+3];
        b[0] = 'A';
        System.out.print("| "+b[0]+" | ");
        for (int i = 1; i < b.length ; i++) {
            b[i] = (char)(b[i-1]+1);
            System.out.print(b[i]+" | ");
        }
        System.out.println();
        return array;
    }
}
