package com.shiming.redisproj;

import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class RedisprojApplicationTests {

    @Autowired
    Redisson redisson;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    void contextLoads() {

        System.out.println("23e23e23");
    }

}
