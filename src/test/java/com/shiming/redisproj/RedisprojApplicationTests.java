package com.shiming.redisproj;

import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootTest
class RedisprojApplicationTests {

    @Autowired
    Redisson redisson;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    JdbcTemplate mysqlJdbcTemplate;

    @Test
    void contextLoads() {

        String name=redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    @Test
    void contextLoads2() {

        List<Map<String,Object>> res=mysqlJdbcTemplate.queryForList("select * from user");

        System.out.println("233232");
    }
}
