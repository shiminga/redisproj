package com.nacos.discovery.consumer.controller;

import com.nacos.discovery.consumer.api.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class EchoController {

    @Resource
    HelloService helloService;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/echo/{str}")
    public String echo(@PathVariable("str")String str){
        return "echo echo echo "+helloService.sayHello(str);
    }
}
