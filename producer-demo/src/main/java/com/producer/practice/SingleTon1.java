package com.producer.practice;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class SingleTon1 {
    private static SingleTon1 instance=new SingleTon1();
    private SingleTon1 (){
        if(instance!=null){
            throw new RuntimeException("实例化中");
        }
    }

    public static SingleTon1 getInstance() {
        if (instance == null) {
            instance = new SingleTon1();
        }
        return instance;
    }

    public static void main(String[] args) {
//        Class clz=SingleTon1.class;
//
//        try {
//            Object i=clz.newInstance();
//
//            System.out.println(i);
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

        Field theUnsafeField = null;
        try {
            theUnsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafeField.setAccessible(true);
            Unsafe unsafeInstance = (Unsafe)theUnsafeField.get(null);
            SingleTon1 instanceA = (SingleTon1)unsafeInstance.allocateInstance(SingleTon1.class);
            System.out.println("2323");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
