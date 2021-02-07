package com.producer.practice;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用Object的wait和notify
 * 多对多生产者和消费者模型
 */
public class ProducerAndConsumer2 {
    int CAPACITY=1<<5;

    volatile int state=0;

    AtomicInteger count=new AtomicInteger(0);

    public void getResource() {
        synchronized (this){
            while (state<=0){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            --state;
            System.out.println(state);
            count.incrementAndGet();
            this.notifyAll();
        }
    }

    public void putResource() {
        synchronized (this){
            while (state>=CAPACITY){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            ++state;
            System.out.println(state);
            count.incrementAndGet();
            this.notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerAndConsumer2 p=new ProducerAndConsumer2();

        for(int i=0;i<1000;++i){
            new Thread(()->{
                p.putResource();
            },"A"+i).start();
        }
        for(int i=0;i<1000;++i){
            new Thread(()->{
                p.getResource();
            },"B"+i).start();
        }

        Thread.sleep(2000);
        System.out.println(p.count);

    }
}
