package com.shiming.redisproj.practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者和消费者测试
 */
public class ProducerAndConsumer {

    ReentrantLock lock=new ReentrantLock();

    Condition notEmpty=lock.newCondition();

    Condition notFull=lock.newCondition();
}
