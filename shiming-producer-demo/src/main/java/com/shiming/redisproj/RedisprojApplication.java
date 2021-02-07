package com.shiming.redisproj;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@EnableAutoConfiguration
public class RedisprojApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisprojApplication.class, args);

    }

}
