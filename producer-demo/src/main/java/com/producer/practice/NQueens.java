package com.producer.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {
    public static List<List<String>> solveNQueens(int n) {
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        List<List<String>> solutions = new ArrayList<List<String>>();
        solve(solutions, queens, n, 0, 0, 0, 0);
        return solutions;
    }

    public static void solve(List<List<String>> solutions, int[] queens, int n, int row, int columns, int diagonals1, int diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
//            System.out.println("找到一种解法"+board);
            solutions.add(board);
        } else {
//            System.out.println("row:"+row);
//            System.out.println("columns:"+Integer.toBinaryString(columns) );
//            System.out.println("diagonals1:"+Integer.toBinaryString(diagonals1) );
//            System.out.println("diagonals2:"+Integer.toBinaryString(diagonals2));
            int availablePositions = ((1 << n) - 1) & (~(columns | diagonals1 | diagonals2));

//            System.out.println("availablePositions = ((1 << n) - 1) & (~(columns | diagonals1 | diagonals2)):"+Integer.toBinaryString(availablePositions) );
            while (availablePositions != 0) {
                int position = availablePositions & (-availablePositions);

//                System.out.println("position = availablePositions & (-availablePositions):"+Integer.toBinaryString(position) );

                availablePositions = availablePositions & (availablePositions - 1);

//                System.out.println("availablePositions = availablePositions & (availablePositions - 1):"+Integer.toBinaryString(availablePositions) );

                int column = Integer.bitCount(position - 1);

//                System.out.println("column = Integer.bitCount(position - 1):"+Integer.toBinaryString(column) );

//                System.out.println("*******************************************");
                queens[row] = column;
                solve(solutions, queens, n, row + 1, columns | position, (diagonals1 | position) << 1, (diagonals2 | position) >> 1);
                queens[row] = -1;
            }
        }
    }

    public static List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        solveNQueens(14);
        System.out.println("耗时："+(System.currentTimeMillis()-start));
    }
}
