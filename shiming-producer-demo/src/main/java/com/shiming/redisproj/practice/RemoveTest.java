package com.shiming.redisproj.practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveTest {

    public static void main(String[] args) {
        String[] ss=new String[]{null};


        for(String str:ss){
            System.out.println(str);
        }

        String regex="(?<=品牌：).+(?=[\u4E00-\u9FA5])";
        regex="(?<=品牌：)[A-Za-z]+";
        Matcher matcher=Pattern.compile(regex).matcher("品牌：SKYWORKS文");
        if(matcher.find()){
            System.out.println(matcher.group());
        }


    }
}
