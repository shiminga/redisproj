package com.shiming.redisproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class RedisprojApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisprojApplication.class, args);
    }

}
