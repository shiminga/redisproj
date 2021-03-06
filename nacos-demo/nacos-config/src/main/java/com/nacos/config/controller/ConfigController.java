package com.nacos.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value(value = "${useLocalCache:false}")
    private boolean useLocalCache;


    @Value(value = "${myName:ming}")
    private String myName;
    /**
     * http://10.1.22.20:8080/config/get
     */
    @RequestMapping("/get")
    public String get() {
        System.out.println(Thread.currentThread().getName()+":"+this);
        return useLocalCache+","+myName;
    }
}
