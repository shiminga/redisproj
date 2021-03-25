package com.nacos.discovery.provider.controller.service;

import com.discovery.api.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    public String sayHello(String id) {
        return "hello nacos:"+id;
    }
}
