package com.shiming.redisproj.practice;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CheesePractice {
    static int CHEESE_CAPICITY = 1000;//奶酪需求数量
    static int WAREHOUSE_CAPICITY = 100;//仓库最大容量
    static int VAN_CAPICITY = 10;//货车最大容量

    int deliveriedNum = 0;//已发数量

    volatile int cheese = 0;//奶酪仓库当前容量

    volatile int milk = 0;//牛奶

    volatile int ferment = 0;//发酵剂

    Object cheese_produce_lock = new Object();

    ReentrantLock warehouse_lock=new ReentrantLock();
    Condition warehouse_empty=warehouse_lock.newCondition();
    Condition warehouse_full=warehouse_lock.newCondition();

    /**
     * 产线生产牛奶
     */
    public void lineProduceMilk() {
        synchronized (cheese_produce_lock) {
            milk++;
            System.out.println("牛奶+1，现有:" + milk);
            cheese_produce_lock.notifyAll();
        }
    }

    /**
     * 产线生产发酵剂
     */
    public void lineProduceferment() {
        synchronized (cheese_produce_lock) {
            ferment++;
            System.out.println("发酵剂+1，现有:" + ferment);
            cheese_produce_lock.notifyAll();
        }
    }

    /**
     * 产线消耗牛奶和发酵剂生产奶酪，并放入仓库
     */
    public void lineProduceCheese() {
        synchronized (cheese_produce_lock) {
            while (milk < 2 || ferment < 1) {
                try {
                    cheese_produce_lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            milk -= 2;
            ferment--;

            try {
                warehouse_lock.lock();
                while (cheese>=1000){
                    warehouse_full.await();
                }
                cheese++;
                warehouse_empty.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                warehouse_lock.unlock();
            }
            System.out.println("奶酪+1，现有:" + cheese);
            cheese_produce_lock.notifyAll();
        }
    }

    /**
     * 货车从仓库拿奶酪
     */
    public void obtainCheese() {
        try {
            warehouse_lock.lock();
            while (cheese<VAN_CAPICITY){
                warehouse_empty.await();
            }

            cheese -= VAN_CAPICITY;
            deliveriedNum += VAN_CAPICITY;
            System.out.println("拿走"+VAN_CAPICITY+"奶酪，还剩：" + cheese);
            System.out.println("已送超市：" + deliveriedNum);
            warehouse_full.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            warehouse_lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        CheesePractice p = new CheesePractice();


            new Thread(() -> {
                for (int i = 0; i < CheesePractice.CHEESE_CAPICITY*2; ++i) {
                    p.lineProduceMilk();
                }
            }, "milk" ).start();

            new Thread(() -> {
                for (int i = 0; i <  CheesePractice.CHEESE_CAPICITY; ++i) {
                    p.lineProduceferment();
                }
            }, "ferment"  ).start();

        new Thread(() -> {
            for (int i = 0; i <  CheesePractice.CHEESE_CAPICITY; ++i) {
                p.lineProduceCheese();
            }
        }, "cheese"  ).start();

        int times=CheesePractice.CHEESE_CAPICITY/CheesePractice.VAN_CAPICITY;
        new Thread(() -> {
            for (int i = 0; i <  times; ++i) {
                p.obtainCheese();
            }
        }, "van"  ).start();

        Thread.sleep(2000);


    }
}
