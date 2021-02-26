package com.producer.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.remoting.exchange.support.DefaultFuture;
import com.api.service.SaleService;
import com.api.util.DistributedLock;
import com.producer.mapper.UserDao;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import reactor.core.Disposable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

@Service(version = "1.0.1")
public class SaleServiceImpl implements SaleService,
        BeanFactoryPostProcessor,
        BeanNameAware,
        BeanFactoryAware,
        ApplicationContextAware,
        BeanPostProcessor,
        InitializingBean,
        Disposable {

    @Autowired
    Redisson redisson;

    @Autowired
    UserDao userDao;

    @Value("${dubbo.registry.address}")
    String config;

    List<String> cache=new ArrayList<>();

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

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        cache.add("****************bean lifecycle beanfactorypostprocessor:"+(redisson==null?"未初始化":"已初始化")+"*****************");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        cache.add("****************bean lifecycle beanFactoryAware:"+(redisson==null?"未初始化":"已初始化")+"*****************");
        cache.add(beanFactory.toString());
    }

    @Override
    public void setBeanName(String name) {
        cache.add("****************bean lifecycle beanNameAware:"+(redisson==null?"未初始化":"已初始化")+"*****************");
        cache.add(name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        cache.add("****************bean lifecycle applicationContextAware:"+(redisson==null?"未初始化":"已初始化")+"*****************");
        cache.add(applicationContext.toString());
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        cache.add("****************bean lifecycle postProcessBeforeInitialization:"+(redisson==null?"未初始化":"已初始化")+"*****************");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        cache.add("****************bean lifecycle postProcessAfterInitialization:"+(redisson==null?"未初始化":"已初始化")+"*****************");
        return bean;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        cache.add("****************bean lifecycle afterPropertiesSet:"+(redisson==null?"未初始化":"已初始化")+"*****************");
    }

    @PostConstruct //==xml的  init-method
    public void postConstruct(){
        cache.add("****************bean lifecycle postConstruct:"+(redisson==null?"未初始化":"已初始化")+"*****************");
    }

    @PreDestroy  //==xml的 destroy-method
    public void preDestroy(){
        cache.add("****************bean lifecycle preDestroy:"+(redisson==null?"未初始化":"已初始化")+"*****************");
    }

    @Override
    public void dispose() {
        cache.add("****************bean lifecycle dispose:"+(redisson==null?"未初始化":"已初始化")+"*****************");
    }

    @Override
    public boolean isDisposed() {
        return false;
    }
}
