package com.producer.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.remoting.exchange.support.DefaultFuture;
import com.api.service.SaleService;
import com.api.util.DistributedLock;
import com.producer.mapper.UserDao;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service(version = "1.0.1")
public class SaleServiceImpl implements SaleService {

    @Autowired
    Redisson redisson;

    @Autowired
    UserDao userDao;

    @Value("${dubbo.registry.address}")
    String config;

    @Override
    public int sale(int saleNum) {
        return zookeeperLock();
    }

    /**
     * zookeeper实现分布式锁
     * @return
     */
    public int zookeeperLock(){
        String keyName="sale20210209001";
        DistributedLock distributedLock=new DistributedLock(config,keyName);
        try {
            distributedLock.lock();
            int num=userDao.getGoodNum(1);
            userDao.decrementWarehouse(--num);
            Thread.sleep(5000);
            System.out.println("****************"+num+"*****************");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            distributedLock.unlock();
        }
        return 1;
    }

    /**
     * 第一种
     * redissionLock
     * 通过lua脚本来做的，支持获取锁超时和锁释放延迟
     * 1.如果指定超时时间则只会设置一次超时，超时则自动释放锁
     * 2.如果不指定超时时间则默认30秒，并启动看门狗，每30/3即10秒把过期时间重置为30秒
     * @return
     */
    public int redissionLock(){
        String keyName="sale20210209001";
        RLock lock =redisson.getLock(keyName);
        try {
            boolean success=lock.tryLock(1,-1,TimeUnit.SECONDS);
            if(success){
                int num=userDao.getGoodNum(1);
                userDao.decrementWarehouse(--num);
//                Thread.sleep(40000);
                System.out.println("****************"+num+"*****************");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return 1;
    }
}
