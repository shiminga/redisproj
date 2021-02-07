package com.shiming.redisproj.practice;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多对多生产者和消费者模型
 */
public class ProducerAndConsumer {

    ReentrantLock lock =new ReentrantLock();

    Condition notEmpty=lock.newCondition();

    Condition notFull=lock.newCondition();

    int CAPACITY=1<<5;

    volatile int state=0;

    AtomicInteger count=new AtomicInteger(0);

    public void getResource(){
        try {
            lock.lock();

            if (state<=0){
                notEmpty.await();
            }
            --state;
            System.out.println(state);
            count.incrementAndGet();

            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void putResource(){
        try {
            lock.lock();
            if (state>=CAPACITY){
                notFull.await();
            }

            ++state;
            System.out.println(state);
            count.incrementAndGet();


            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerAndConsumer p=new ProducerAndConsumer();

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

        synchronized (ProducerAndConsumer.class){

            ProducerAndConsumer.class.wait();
        }
    }
}
