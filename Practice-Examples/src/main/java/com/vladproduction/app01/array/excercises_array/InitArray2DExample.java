package com.vladproduction.app01.array.excercises_array;

public class InitArray2DExample {
    public static void main(String[] args) {

        int[][] A={{1,2,3},{4,5,6}};
        int[][] B={{1,2},{3,4},{5,6}};
        int[][] C={{1},{2,3},{4,5,6}};

        System.out.println("A [][]");
        show(A);
        System.out.println("B [][]");
        show(B);
        System.out.println("C [][]");
        show(C);
    }

    static void show(int [][] a){
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
