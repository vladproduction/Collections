package com.vladproduction.app01.array.excercises_array;

public class ArrayDegree2 {
    public static void main(String[] args) {

        int t  = 2;
        int [] a = new int[5];
        for (int i = 0; i < a.length; i++) {

            a[i] = degree(t,i);
            System.out.print(a[i] + " ");

        }
        System.out.println();
//        int res = degree(2,5);
//        System.out.println(res);
    }

    static int degree(int a, int b) {

        int res = 1;
        for (int i = 1; i <= b; i++) {
            res *= a;
        }
        return res;
    }
}
//1 2 4 8 16