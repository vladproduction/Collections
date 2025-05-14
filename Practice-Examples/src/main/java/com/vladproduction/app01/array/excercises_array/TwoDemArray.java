package com.vladproduction.app01.array.excercises_array;

public class TwoDemArray {
    public static void main(String[] args) {
        double [][] myArray = new double[3][4]; //how to initialise a new [][]
        //[3] - rows; [4] - colons


        //fill matrix 3*3 by random numbs 0-9
        String text = "";
        int n = 3;
        int m = 3;
        int [][] num = new int [n][m];
        int nLines = num.length; // how many lines(rows)
        int mColons = num [0].length; // how many colons
        System.out.println("nLines = "+nLines);
        System.out.println("mColons = "+mColons);
        for (int i = 0; i<n; i++){
           text += "\n";
            for(int j = 0; j<m; j++ ){
                num[i][j] = (int)(10*Math.random());
                text += num [i][j] + " ";
            }
        }
        System.out.println(text);
    }
}
