package com.producer;

import com.producer.mapper.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;

@SpringBootTest
class ProducerApplicationTests {

    @Autowired
    UserDao userDao;
//
    @Test
    void contextLoads() {

        for(int i=0;i<500;i++){
            new Thread(()->{
                int num=userDao.getGoodNum(1);
                userDao.decrementWarehouse(--num);
            }).start();
        }
    }

    @Test
    void contextLoads2() {

        System.out.println(2147364747/1024/1024);

    }

    @Test
    void contextLoads3(){
        String str="create table 'payment' ( 'id' bigint(20) not null auto_increment comment 'ID', 'serial' varchar(200) default '', primary key('id') ) engine=innodb auto_i_increment=1 default charset=utf8;";

//        mysqlJdbcTemplate.update(str);
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