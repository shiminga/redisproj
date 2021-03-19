package com.producer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaProducerConfig {

    @Autowired
    KafkaProperties kafkaProperties;

    @Bean
    public ProducerFactory<Integer, String> producerFactory() {
        DefaultKafkaProducerFactory factory = new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties());
        factory.transactionCapable();
        factory.setTransactionIdPrefix("tran-shim-");
        return factory;
    }

    @Bean
    public KafkaTemplate kafkaTemplate(@Autowired ProducerFactory producerFactory){
        return new KafkaTemplate(producerFactory);
    }

    @Bean(name = "kafkaTransactionManager")
    public KafkaTransactionManager transactionManager(@Autowired ProducerFactory producerFactory) {
        KafkaTransactionManager manager = new KafkaTransactionManager(producerFactory);
        return manager;
    }
}
