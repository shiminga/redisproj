package com.producer.practice;

import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.locks.LockSupport;

public class InterruptTest implements InitializingBean {

    public static final Object lock=new Object();

    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            long cur=System.currentTimeMillis();
            while(System.currentTimeMillis()-cur<1000){
                System.out.println("running");
            }
            LockSupport.park(lock);
            System.out.println("回复了");
            LockSupport.park();
            while(true){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("running");
            }
        });
        t1.start();

        LockSupport.unpark(t1);
        LockSupport.unpark(t1);
        LockSupport.unpark(t1);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

}
