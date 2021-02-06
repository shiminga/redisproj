package com.shiming.redisproj.practice;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class CuttingRope {

    public static int cuttingRope(int n) {
        if(n==2){return 1;}
        BigInteger[] dp=new BigInteger[n+1];
        Arrays.fill(dp,BigInteger.valueOf(1));

        for(int i=3;i<=n;++i){
            for(int j=1;j<i;++j){
                    dp[i]=dp[i].max(dp[j].max(BigInteger.valueOf(j)).multiply(dp[i-j].max(BigInteger.valueOf(i-j))));
            }
        }

        return dp[n].intValue()%1000000007;
    }

    public static List<List<Integer>> generate(int numRows) {
        if(numRows==0){return new ArrayList<List<Integer>>();}

        List<List<Integer>> res=new ArrayList<>();
        List<Integer> one=new ArrayList<>();
        one.add(1);
        res.add(one);

        for(int i=1;i<numRows;++i){
            List<Integer> generateRow=new ArrayList<>();
            generateRow.add(1);
            for(int j=0;j<one.size();++j){
                if(j==one.size()-1){
                    generateRow.add(1);
                }else{
                    generateRow.add(one.get(j)+one.get(j+1));
                }
            }
            res.add(generateRow);
            one=generateRow;
        }
        return res;
    }

    public static void main(String[] args) {

        /**
         * 出库明细   line_no  item_code qty
         *
         *
         *
         */

        List<List<Integer>> res=generate(5);

        System.out.println("234");

    }
}
