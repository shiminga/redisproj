package com.producer.practice.forkjoin;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkjoinTest extends RecursiveTask<Integer> {

    static final Set<Thread> set=new HashSet<>();
    int startVal,endVal;

    ForkjoinTest(int startVal, int endVal){
        this.startVal=startVal;
        this.endVal=endVal;
    }


    @Override
    protected Integer compute() {
        int startVal=this.startVal;
        int endVal=this.endVal;

        set.add(Thread.currentThread());
        if(endVal-startVal<=10){
            int sum=0;
            for(int i=startVal;i<=endVal;i++){
                sum+=i;
            }
            return sum;
        }else{
            int mid=(startVal+endVal)>>>1;
            ForkjoinTest f1=new ForkjoinTest(startVal,mid);
            f1.fork();

            ForkjoinTest f2=new ForkjoinTest(mid+1,endVal);
            f2.fork();

            return f1.join()+f2.join();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int start=1000;
        for(int i=1000000;i<1000000000;i*=10){
            forkjoinFunc(start,i);
            normalFunc(start,i);
        }

    }

    public static void forkjoinFunc(int startVal,int endVal) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool=new ForkJoinPool();

        System.out.println("*******************forkjoin开始+"+startVal+","+endVal+"********************");
        long startTime=System.currentTimeMillis();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(new ForkjoinTest(startVal, endVal));
        System.out.println("计算结果"+submit.get().intValue());
        System.out.println("线程数"+ForkjoinTest.set.size());
        System.out.println("*******************forkjoin结束+"+startVal+","+endVal+"，耗时"+(System.currentTimeMillis()-startTime)+"********************");
    }

    public static void normalFunc(int startVal,int endVal) {
        System.out.println("*******************循环开始+"+startVal+","+endVal+"********************");
        long startTime=System.currentTimeMillis();

        int sum=0;
        for(int i=startVal;i<=endVal;i++){
            sum+=i;
        }
        System.out.println("计算结果"+sum);
        System.out.println("*******************循环结束+"+startVal+","+endVal+"，耗时"+(System.currentTimeMillis()-startTime)+"********************");
    }
}
