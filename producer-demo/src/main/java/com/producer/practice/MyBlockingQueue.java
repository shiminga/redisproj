package com.producer.practice;

import sun.misc.InvalidJarIndexException;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<T> {

    LinkedList<T> list=new LinkedList();

    int cap;

    Lock lock=new ReentrantLock();

    Condition notFull=lock.newCondition();//生产者
    Condition notEmpty=lock.newCondition();//消费者

    MyBlockingQueue(int capacity){
        this.cap=capacity;
    }

    public T poll(){
        T t=null;
        try {
            lock.lock();
            while (list.size()==0){
                notEmpty.await();
            }
            t=list.poll();
            notFull.signal();
            System.out.println(Thread.currentThread().getName()+"消费了:"+t+"剩余："+list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public Boolean offer(T t){
        Boolean success=false;
        try {
            lock.lock();

            while(list.size()>=cap){
                notFull.await();
            }
            list.offer(t);
            notEmpty.signal();
            success=true;
            System.out.println(Thread.currentThread().getName()+"生产了:"+t+"剩余："+list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return success;
    }

    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueue<Integer> myBlockingQueue=new MyBlockingQueue<>(5);


        for(int i=0;i<50;i++){
            final int index=i;
            new Thread(()->{
                myBlockingQueue.offer(index);
            },"生产者"+i).start();
        }

        for(int i=0;i<50;i++){
            new Thread(()->{
                myBlockingQueue.poll();
            },"消费者"+i).start();
            Thread.sleep(1000);
        }
    }
}
