package com.shiming.redisproj.practice;

/**
 * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
 *
 * 返回的长度需要从小到大排列。
 *
 * 示例：
 *
 * 输入：
 * shorter = 1
 * longer = 2
 * k = 3
 * 输出： {3,4,5,6}
 * 提示：
 *
 * 0 < shorter <= longer
 * 0 <= k <= 100000
 *
 */
public class DivingBoard {

    public static int[] divingBoard(int shorter, int longer, int k) {
        if(k==0){
            return new int[]{};
        }
        if(shorter==longer){
            return new int[]{shorter*k};
        }
        int[] res=new int[k+1];
        res[0]=shorter*k;
        int dVal=longer-shorter;
        for(int i=1;i<=k;i++){
            res[i]=res[i-1]+dVal;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nis= divingBoard(2,
                1118596,
                979);

        System.out.println("3233");

        System.out.println(" 23 34 ".trim());

    }
}
