package com.producer.service.impl;

import com.producer.config.ConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class KafkaSender {

    Logger log=LoggerFactory.getLogger(KafkaSender.class);

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Autowired
    ConfigProperties configProperties;

    private static AtomicInteger count=new AtomicInteger(0);

    @Transactional(transactionManager = "kafkaTransactionManager",rollbackFor = Exception.class)
    public void send(String msg) throws ExecutionException, InterruptedException {
        log.info("send:{}",msg);
        log.info("main threadid:{}",Thread.currentThread().getName());
        final long startTime=System.currentTimeMillis();
        /**
         * 主动把缓冲区推送到kafka
         */
//        kafkaTemplate.flush();
        /**
         * 同步投递
         */
        Object o = kafkaTemplate.send(configProperties.getTopic(), msg);
        count.incrementAndGet();
        System.out.println("投递结果"+this+Thread.currentThread().getName()+","+count.intValue());

        /**
         * 异步投递
         */
//        ListenableFuture future = kafkaTemplate.send(configProperties.getTopic(), msg);
//        future.addCallback(new SuccessCallback() {
//            @Override
//            public void onSuccess(Object result) {
//                System.out.println("回调result:"+result);
//                log.info("child threadid:{}",Thread.currentThread().getName());
//                System.out.println("耗时："+(System.currentTimeMillis()-startTime)+"发送成功");
//            }
//        }, new FailureCallback() {
//            @Override
//            public void onFailure(Throwable ex) {
//                System.out.println("耗时："+(System.currentTimeMillis()-startTime)+"发送失败");
//            }
//        });
    }
}
