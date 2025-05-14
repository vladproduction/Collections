package com.vladproduction.app01.array.excercises_array;

public class Array2DExample {
    public static void main(String[] args) {
        int m = 2, n = 4;
        int [][] a = new int[3][5];
        char[][] b = new char[m][n];
        int val = 1;
        char s = 'A';
        System.out.println("Digit [][]:");
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = val;
                val++;
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("-------------------");
        System.out.println("char [][]");
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                b[i][j] = s;
                s++;
                System.out.print(b[i][j] + " ");
            }
            System.out.println();
        }
    }
}
