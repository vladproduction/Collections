package com.vladproduction.app01.array.excercises_array;

public class ArrayDegree2Variant {
    public static void main(String[] args) {

        int t = 2;
        int [] a = degree(t,5);

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    static int[] degree(int a, int b) {
        int[] array = new int[b+1];
        int res = 1;
        array[0] = res;
        for (int i = 1; i <= b; i++) {
            res *= a;
            array[i] = res;
        }
        return array;
    }
}
//1 2 4 8 16