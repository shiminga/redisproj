package com.producer.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.api.service.SaleService;
import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.1")
public class SaleServiceImpl implements SaleService {

    @Autowired
    Redisson redisson;

    @Override
    public int sale(int saleNum) {
//        RedissonRedLock lock=redisson.
        return 0;
    }
}
