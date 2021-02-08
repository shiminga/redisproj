package com.producer.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.api.service.SaleService;
import com.producer.mapper.UserDao;
import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service(version = "1.0.1")
public class SaleServiceImpl implements SaleService {

    @Autowired
    Redisson redisson;

    @Autowired
    UserDao userDao;

    @Override
    public int sale(int saleNum) {
//        userDao.decrementWarehouse(1);
        List<Thread> threads=new LinkedList<>();
        for(int i=0;i<500;i++){
            threads.add(new Thread(()->{
                synchronized (this){
                    int num=userDao.getGoodNum(1);
                    userDao.decrementWarehouse(--num);
                    System.out.println("****************"+num+"*****************");
                }
            }));
        }

        for(Thread t:threads){
            t.start();
        }
        return 1;
    }
}
