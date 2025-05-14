package com.vladproduction.app01.array.excercises_array;

public class TransposeArrayDemo {
    static void show(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int i, j, n;
        System.out.println("Before:");
        show(a);

        for (i = 1; i < a.length; i++) {
            for (j = 0; j < i; j++) {
                n = a[i][j];
                a[i][j] = a[j][i];
                a[j][i] = n;
            }
        }
        System.out.println("After:");
        show(a);
    }


}
