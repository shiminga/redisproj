package com.discovery.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "hello-service")
public interface HelloService {

    @GetMapping("/say/{id}")
    public String sayHello(@PathVariable("id") String id);
}
