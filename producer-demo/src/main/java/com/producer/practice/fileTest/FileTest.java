package com.producer.practice.fileTest;

import java.io.*;
import java.nio.CharBuffer;

public class FileTest {
    public static final String path="C:\\Users\\shim\\Desktop\\access.log";

    public static final String targetPath="C:\\Users\\shim\\Desktop\\log\\";

    public static long  bytesPerFile=1024*1024*50;

    public static long  totalLength;

    public static long  currentLength=0;

    public static void main(String[] args) throws FileNotFoundException {
        File originFile=new File(path);
        totalLength=originFile.length();
        System.out.println("总文件大小："+totalLength+"字节");

        try (FileReader reader=new FileReader(originFile)){
            int seq=1;
            char[] cs=new char[65535];

            while(currentLength<totalLength){
                File file=new File(targetPath+"access"+seq+++".log");
                System.out.println("创建文件:"+file.getName());
                if(!file.exists()){
                    file.createNewFile();
                }
                FileWriter fileWriter=new FileWriter(file);
                while(file.length()<bytesPerFile){
                    //读
                    reader.read(cs);

                    //写
                    fileWriter.write(cs);;
                    fileWriter.flush();
                    System.out.println("拷贝ing::"+file.length());
                }
                currentLength+=file.length();
                fileWriter.close();
            }
        }catch (Exception e){

        }
    }
}
