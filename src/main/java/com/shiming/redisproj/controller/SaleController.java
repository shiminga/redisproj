package com.shiming.redisproj.controller;

import com.shiming.redisproj.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/sale")
public class SaleController {

    @Autowired
    SaleService saleService;

    @GetMapping("/psale")
    @ResponseBody
    public String sale(){
        int num= saleService.sale(1);
        return num==0?"您来慢了，已经被抢完了":"恭喜您，抢到了！";
    }
}
