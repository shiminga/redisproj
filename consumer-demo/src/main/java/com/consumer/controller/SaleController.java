package com.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.api.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/sale")
@Controller
public class SaleController {

    @Reference(version = "1.0.1",timeout = 41000)
    SaleService saleService;

    @GetMapping("/psale")
    @ResponseBody
    public String sale(){
        int num= saleService.sale(1);
        return num==0?"您来慢了，已经被抢完了":"恭喜您，抢到了！";
    }
}
