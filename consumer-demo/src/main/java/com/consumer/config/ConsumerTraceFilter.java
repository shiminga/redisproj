package com.consumer.config;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.api.util.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Activate(group = Constants.CONSUMER)
public class ConsumerTraceFilter implements com.alibaba.dubbo.rpc.Filter {

    Logger logger=LoggerFactory.getLogger(ConsumerTraceFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcInvocation rpcInvocation=(RpcInvocation)invocation;
        rpcInvocation.setAttachment("sessionid",RpcContext.getContext().getAttachment("sessionid"));
        return invoker.invoke(invocation);
    }
}
