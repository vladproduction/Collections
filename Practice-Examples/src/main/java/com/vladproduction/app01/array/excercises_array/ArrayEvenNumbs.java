package com.vladproduction.app01.array.excercises_array;

public class ArrayEvenNumbs {
    public static void main(String[] args) {
        int n = 10;
        int val = 1;
        int [] a = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = val*2;
            val++;
            System.out.print(a[i]+"\t");
        }
        System.out.println();
    }
}
//2 4