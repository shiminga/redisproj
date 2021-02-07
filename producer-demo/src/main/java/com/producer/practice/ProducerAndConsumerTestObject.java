package com.producer.practice;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用Object的wait和notify
 * 多对多生产者和消费者模型
 */
public class ProducerAndConsumerTestObject {

    volatile int state=0;

    AtomicInteger count=new AtomicInteger(0);

    public void getResource() {
        synchronized (this){
            if(state<=0){
                try {
                    System.out.println("get 睡之前");
                    this.wait();
                    System.out.println("get 睡之后");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            --state;
            System.out.println(state);
            count.incrementAndGet();
            this.notify();
        }
    }

    public void putResource() {
        synchronized (this){
            if(state>=1){
                try {
                    System.out.println("put 睡之前");
                    this.wait();
                    System.out.println("put 睡之后");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            ++state;
            System.out.println(state);
            count.incrementAndGet();
            this.notify();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerAndConsumerTestObject p=new ProducerAndConsumerTestObject();

        Thread tb=new Thread(()->{
            p.getResource();
        },"B");
        tb.start();

        Thread.sleep(1000);
        new Thread(()->{
            p.putResource();
        },"A").start();


    }
}
