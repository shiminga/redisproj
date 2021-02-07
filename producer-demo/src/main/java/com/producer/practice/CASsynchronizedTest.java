package com.producer.practice;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class CASsynchronizedTest {


    static final class Treebin{

        public Treebin() {
            this.lockState = 5;
        }

        volatile int lockState;

        public int getLockState() {
            return lockState;
        }

        public void setLockState(int oldVal,int newVal) {
            U.compareAndSwapInt(this,lockState,oldVal,newVal);
            System.out.println("我进来改值了");
        }


        public void testObtain(){

            System.out.println("进来了同步方法");
            synchronized (this){
                try {
                    System.out.println(Thread.currentThread().getName()+"开始休眠");
                    Thread.sleep(10000);
                    System.out.println(Thread.currentThread().getName()+"结束休眠");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private static final Unsafe U;
        private static final long LOCKSTATE;
        static {
            try {
                Field f=Unsafe.class.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                U= (Unsafe) f.get(null);

                Class<?> k = CASsynchronizedTest.Treebin.class;
                LOCKSTATE = U.objectFieldOffset
                        (k.getDeclaredField("lockState"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }
    }
}
