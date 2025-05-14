package com.vladproduction.app01.array.array_2D;

import java.util.Arrays;

public class Array_2D {
    public static void main(String[] args) {
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
        board[0][0] = 'O';
        board[1][0] = 'O';
        board[2][0] = 'O';
        System.out.println(Arrays.deepToString(board));
        System.out.println("-------in line initialization-------------");
        char[][] boardTwo = new char[][]{
                new char[] {'O', '-', '-'},
                new char[] {'O', '-', '-'},
                new char[] {'O', '-', '-'}
        };
        System.out.println(Arrays.deepToString(boardTwo));
    }
}
