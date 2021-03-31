package com.nacos.discovery.provider.controller.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl {

    @Value("${server.port}")
    String port;

    public String sayHello(String id) {
        return "hello nacos:"+port+","+id;
    }
}
