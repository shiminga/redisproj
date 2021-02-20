package com.producer.config;

import org.redisson.Redisson;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RedisConfig {
    @Autowired
    private RedisConfigProperties redisConfigProperties;

    //添加redisson的bean
    @Bean
    public Redisson redisson() {
        //redisson版本是3.5，集群的ip前面要加上“redis://”，不然会报错，3.2版本可不加
        List<String> clusterNodes = new ArrayList<>();
        for (int i = 0; i < redisConfigProperties.getCluster().getNodes().size(); i++) {
            clusterNodes.add("redis://" + redisConfigProperties.getCluster().getNodes().get(i));
        }
        Config config = new Config();
        ClusterServersConfig clusterServersConfig = config.useClusterServers()
                .addNodeAddress(clusterNodes.toArray(new String[clusterNodes.size()]));
        clusterServersConfig.setPassword(redisConfigProperties.getPassword());//设置密码

        //设置看门狗的超时时间
        config.setLockWatchdogTimeout(3000);
        return (Redisson) Redisson.create(config);
    }
}
