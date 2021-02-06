package com.shiming.redisproj.practice;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    static CountDownLatch countDownLatch=new CountDownLatch(4);
    static int[] nums=new int[4];

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<4;++i){
            final int j=i;
            new Thread(()->{
                long curTime=System.currentTimeMillis();
                while (System.currentTimeMillis()-curTime<1000){
                        nums[j]++;
                }
                countDownLatch.countDown();
            },(char)('A'+i)+"").start();
        }

        for(int i=0;i<2;++i){
            final int j=i;
            new Thread(()->{
                long curTime=System.currentTimeMillis();
                while (System.currentTimeMillis()-curTime<1000){
                    nums[j]++;
                }
                try {
                    System.out.println("开始阻塞");
                    countDownLatch.await();
                    System.out.println("结束阻塞");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },(char)('B'+i)+"").start();
        }

        Thread.sleep(1000);
        int sum=0;
        for(int i:nums){
            sum+=i;
        }
        System.out.println(sum);
    }
}
