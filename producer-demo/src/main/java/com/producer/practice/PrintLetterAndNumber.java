package com.producer.practice;

public class PrintLetterAndNumber {

    static volatile  boolean isLetter=true;
    Object lock=new Object();

     Thread letterThread=new Thread(()->{
        int i=0;
        while (i<26){
            synchronized (lock){
                while (!isLetter){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println((char) ('A'+i++));
                isLetter=false;
                lock.notify();
            }
        }
    },"letter");

      Thread numberThread=new Thread(()->{
        int i=0;
        while (i<26){
            synchronized (lock){
                while (isLetter){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                isLetter=true;
                System.out.println( (++i));
                lock.notify();
            }
        }
    },"number");

    {
        letterThread.start();
        numberThread.start();
    }

    public static void main(String[] args) {
        PrintLetterAndNumber printLetterAndNumber=new PrintLetterAndNumber();
    }
}
