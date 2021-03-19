package com.consumer.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaConsumer {
    Logger log=LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = {"distribute_trans"},containerFactory = "kafkaListenerContainerFactory")
    public void listen(List<ConsumerRecord> consumerRecords, Acknowledgment ack){
        int offset=0;
        boolean success=true;
        try {
            for(ConsumerRecord consumerRecord:consumerRecords){
                System.out.println("收到消息："+consumerRecord);
                if(offset==1){throw new RuntimeException();}
                offset++;
            }
        }catch (Exception e){
            success=false;
//            ack.nack(offset,100);
        }finally {
            if(success){
                ack.acknowledge();
            }
        }
    }
}
