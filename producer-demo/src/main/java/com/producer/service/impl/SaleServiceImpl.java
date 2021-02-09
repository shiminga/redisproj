package com.producer.service.impl;

import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.remoting.exchange.support.DefaultFuture;
import com.api.service.SaleService;
import com.producer.mapper.UserDao;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Service(version = "1.0.1")
public class SaleServiceImpl implements SaleService {

    @Autowired
    Redisson redisson;

    @Autowired
    UserDao userDao;

    @Override
    public int sale(int saleNum) {
        String keyName="sale20210209001";
        RLock lock =redisson.getLock(keyName);
        try {
            boolean success=lock.tryLock(1,3,TimeUnit.SECONDS);
            if(success){
                int num=userDao.getGoodNum(1);
                userDao.decrementWarehouse(--num);
                Thread.sleep(2000);
                System.out.println("****************"+num+"*****************");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
            DefaultFuture ni=null;
        }
        return 1;
    }

    /**
     * //        userDao.decrementWarehouse(1);
     *         List<Thread> threads=new LinkedList<>();
     *         for(int i=0;i<500;i++){
     *             threads.add(new Thread(()->{
     *                 synchronized (this){
     *                     int num=userDao.getGoodNum(1);
     *                     userDao.decrementWarehouse(--num);
     *                     System.out.println("****************"+num+"*****************");
     *                 }
     *             }));
     *         }
     *
     *         for(Thread t:threads){
     *             t.start();
     *         }
     */
}
