package com.shiming.redisproj.practice;

import sun.plugin.javascript.navig.LinkArray;

import java.lang.annotation.Target;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

public class SolutionRotate {
    public static void rotate(int[][] matrix) {
        int row=matrix.length,col=matrix[0].length;
        for(int i=0;i<row;i++){
            for(int j=0;j<col>>>1;j++){
                int tmp=matrix[i][j];
                matrix[i][j]=matrix[i][col-j-1];
                matrix[i][col-j-1]=tmp;
            }
        }

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(i<row>>>1||j<col>>>1){
                    int tmp=matrix[i][j];
                    matrix[i][j]=matrix[col-j-1][row-i-1];
                    matrix[col-j-1][row-i-1]=tmp;
                }
            }
        }
        String s=new String(new char[]{'a','\0','d'});
        System.out.println("234343");



    }


    public static void main(String[] args) {
        int[][] matrix= {
                { 5, 1, 9,11},
                {2, 4, 8,10},
                {13, 3, 6, 7},
                {15,14,12,16}
        };

        List<List<Integer>> triangle=new ArrayList<>();
        List<Integer> l1=new ArrayList<>();
        List<Integer> l2=new ArrayList<>();
        List<Integer> l3=new ArrayList<>();
        List<Integer> l4=new ArrayList<>();

        /**
         *  2
         *   3 4
         *  6 5 7
         * 4 1 8 3
         */
        l1.add(2);
        l2.add(3);
        l2.add(4);

        l3.add(6);
        l3.add(5);
        l3.add(7);

        l4.add(4);
        l4.add(1);
        l4.add(8);
        l4.add(3);
        triangle.add(l1);
        triangle.add(l2);
        triangle.add(l3);
        triangle.add(l4);

        minimumTotal(triangle);

    }

    public static String test(){
        int i=1;

        try {
            return "a"+(++i);
        }catch (Exception e){
            return "b"+(++i);
        }finally {
            return "c"+(++i);
        }
    }

    public static int minimumTotal(List<List<Integer>> triangle) {

        if(triangle==null||triangle.size()==0){return 0;}

        int length=triangle.size();
        int[] dp=new int[length];
        dp[0]=triangle.get(0).get(0);
        int min=Integer.MAX_VALUE;

        for(int i=1;i<length;i++){
            List<Integer> tmp=triangle.get(i);
            for(int j=0;j<=i;j++){
                if(j==0){
                    dp[j]+=tmp.get(j);
                    System.out.println(dp[j]);
                }else if(j==i){
                    dp[j]=dp[j-1]+tmp.get(j);
                    System.out.println(dp[j]);
                }else{
                    dp[j]=Math.min(dp[j-1],dp[j])+tmp.get(j);
                    System.out.println(dp[j]);
                }
                if(i==length-1){
                    min=Math.min(min,dp[j]);
                    System.out.println(min);
                }
            }
        }
        return min;
    }
}
