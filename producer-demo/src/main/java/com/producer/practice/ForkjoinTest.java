package com.producer.practice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkjoinTest extends RecursiveTask<Integer> {

    int startVal,endVal;

    ForkjoinTest(int startVal, int endVal){
        this.startVal=startVal;
        this.endVal=endVal;
    }


    @Override
    protected Integer compute() {
        int startVal=this.startVal;
        int endVal=this.endVal;

        if(endVal-startVal<=10){
            int sum=0;
            while(startVal<=endVal){
                sum+=startVal++;
            }
            System.out.println(sum);
            return sum;
        }else{
            ForkjoinTest f1=new ForkjoinTest(startVal,(startVal+endVal)>>>1);
            f1.fork();

            ForkjoinTest f2=new ForkjoinTest((startVal+endVal)>>>1+1,endVal);
            f2.fork();

            return f1.join()+f2.join();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool=new ForkJoinPool();

        ForkJoinTask<Integer> submit = forkJoinPool.submit(new ForkjoinTest(1, 1001));
        System.out.println(submit.get().intValue());;
    }
}
