package com.producer.practice;

import java.util.concurrent.locks.LockSupport;

public class PrintOddEven {

    public static volatile int count=1;
    public static  final  int thresh=20;

    static Thread t1;
    static Thread t2;
    public static void main(String[] args) {

        t1=new Thread(()->{
            while(count<thresh){
                if((count&1)==0){LockSupport.park();}

                System.out.println(count++);
                LockSupport.unpark(t2);
            }
        },"odd");
        t2=new Thread(()->{
            LockSupport.park();
            while(count<thresh){
                if((count&1)!=0){LockSupport.park();}
                System.out.println(count++);
                LockSupport.unpark(t1);
            }
        },"even");

        t1.start();
        t2.start();
    }
}
