package com.shiming.redisproj.practice;

import java.util.Random;

public class HuaHuaTest {

    public static void main(String[] args) {
        int i=1;

        while (i++<10){
            System.out.println(generateStr());
        }

    }

    /**
     * 学号，姓名，性别，年级，班号,科目，成绩
     */
    static class Grade{

    }

    public static String generateStr(){
        String zh_cn = "";
        String str ="";

        // Unicode中汉字所占区域\u4e00-\u9fa5,将4e00和9fa5转为10进制
        int start = Integer.parseInt("4e00", 16);
        int end = Integer.parseInt("9fa5", 16);

        int n=(new Random()).nextInt(2)+1;
        for(int ic=0;ic<n;ic++){
            // 随机值
            int code = (new Random()).nextInt(end - start + 1) + start;
            // 转字符
            str = new String(new char[] { (char) code });
            zh_cn=zh_cn+str;
        }
        return zh_cn;
    }
}
