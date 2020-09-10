#以配置文件启动redis
#docker run --net=host
#-p 6379:6379  端口映射：前表示主机部分，：后表示容器部分
#--name redis_6379   指定该容器名称，查看和进行操作都比较方便
#-v /var/lib/docker/redis-cluster-d/redis/redis-6379/redis-6379.conf:/etc/redis/redis.conf     -v 挂载目录，规则与端口映射相同
#-v /var/lib/docker/redis-cluster-d/redis/redis-6379/data:/data
#-d  redis      -d redis 表示后台启动redis
# redis-server /etc/redis/redis.conf   以配置文件启动redis，加载容器内的conf文件，最终找到的是挂载的目录

#启动集群
#redis-cli -a 123456  --cluster create 8.129.178.38:6379 8.129.178.38:6380 8.129.178.38:6381 8.129.178.38:6382 8.129.178.38:6383 8.129.178.38:6384 --cluster-replicas 1

2020-09-10
1.阿里云服务器上用的docker搭建的redis集群，不知道为什么通过端口映射的方式为
什么启动集群的时候一直Waiting for the cluster to join，最后还是通过--net=host启动以宿主共享网络的方式建立的连接
