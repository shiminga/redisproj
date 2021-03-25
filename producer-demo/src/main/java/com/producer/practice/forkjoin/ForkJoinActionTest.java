package com.producer.practice.forkjoin;

import java.util.concurrent.RecursiveAction;

public class ForkJoinActionTest extends RecursiveAction {
    int startVal,endVal;

    ForkJoinActionTest(int startVal,int endVal){
        this.startVal=startVal;
        this.endVal=endVal;
    }

    @Override
    protected void compute() {
        int startVal=this.startVal;
        int endVal=this.endVal;

        if(endVal-startVal<=10){
            for(int i=startVal;i<=endVal;i++){
                System.out.println(i);
            }
        }else{
            int mid=(startVal+endVal)>>>1;
            ForkJoinActionTest f1=new ForkJoinActionTest(startVal,mid);
            f1.fork();

            ForkJoinActionTest f2=new ForkJoinActionTest(mid+1,endVal);
            f2.fork();

            f1.join();f2.join();
        }
    }

    public static void main(String[] args) {
        ForkJoinActionTest forkJoinActionTest=new ForkJoinActionTest(1,100);
        forkJoinActionTest.compute();
    }
}
