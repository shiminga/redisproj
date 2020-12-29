package com.shiming.redisproj.practice;

import java.io.*;
import java.lang.reflect.Constructor;

public class ChenboTest {


    public static void main(String[] args) throws IOException{

        String path="C:\\Users\\shim\\Desktop\\testObject.txt";
        File file=new File(path);
        if(!file.exists()){
            file.createNewFile();
        }

        try (
                FileOutputStream fos=new FileOutputStream(path);
                ObjectOutputStream oos=new ObjectOutputStream(fos);

                FileInputStream fis=new FileInputStream(path);
                ObjectInputStream ois=new ObjectInputStream(fis);
        ){

//            SingleTon singleTon=SingleTon.GetInstance();
//            System.out.println(singleTon);
//            oos.writeObject(singleTon);
//
//            SingleTon ss=(SingleTon)ois.readObject();
//            System.out.println(ss);
//
//
//            /**
//             * 私有化构造方法可以使私有化的构造方法无效
//             */
//            Class clz=singleTon.getClass();
//            Constructor constructor=clz.getDeclaredConstructor();
//            constructor.setAccessible(true);
//            SingleTon singleTon1=(SingleTon) constructor.newInstance();
//            System.out.println(singleTon1);
//            System.out.println("223423");


            /**
             * ***************************************************************
             */
            T t=T.getInstance();
            System.out.println(t);
            oos.writeObject(t);

            T tt=(T)ois.readObject();
            System.out.println(tt);


            /**
             * 私有化构造方法可以使私有化的构造方法无效
             */
            Class clz=t.getClass();
            Constructor constructor=clz.getDeclaredConstructor();
            constructor.setAccessible(true);
            T ttt=(T) constructor.newInstance();
            System.out.println(ttt);
            System.out.println("223423");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * 静态内部类单例
 */
 class SingleTon implements Serializable{

    private SingleTon(){
        if(SingletonInner.instance!=null){
            throw new RuntimeException("实例化中");
        }
    }

    static class SingletonInner{
        private static SingleTon instance=new SingleTon();
    }

    public static SingleTon  GetInstance(){
        return SingletonInner.instance;
    }

    public Object readResolve() throws ObjectStreamException {
        return SingletonInner.instance;
    }
 }

class   T implements Serializable{

     private T(){
     }

    private enum inner{
        INSTANCE;

        private T t;

        inner(){
            t=new T();
        }

        private T getInstance(){
            return t;
        }
    }

    public static T getInstance(){
         return inner.INSTANCE.getInstance();
    }
}
