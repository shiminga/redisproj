package com.shiming.redisproj.service.impl;

import com.shiming.redisproj.service.SaleService;
import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.springframework.beans.factory.annotation.Autowired;

public class SaleServiceImpl implements SaleService {

    @Autowired
    Redisson redisson;

    @Override
    public int sale(int saleNum) {
//        RedissonRedLock lock=redisson.
        return 0;
    }
}
