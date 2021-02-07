package com.shiming.redisproj;

import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;
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

        Student student=new Student();
        student.setName("石明");
        student.setAge(25);

        redisTemplate.opsForHash().put("shim","stu","hahahah");


        Object o=redisTemplate.opsForHash().get("shim","stu");

        System.out.println("e44");
    }

    @Test
    void contextLoads2() {

        System.out.println(2147364747/1024/1024);

    }

    @Test
    void contextLoads3(){
        String str="create table 'payment' ( 'id' bigint(20) not null auto_increment comment 'ID', 'serial' varchar(200) default '', primary key('id') ) engine=innodb auto_i_increment=1 default charset=utf8;";

        mysqlJdbcTemplate.update(str);
        System.out.println(str.toUpperCase());
    }
}
class Student implements Serializable {
    String name;

    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}