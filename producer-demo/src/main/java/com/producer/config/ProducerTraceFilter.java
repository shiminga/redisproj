package com.producer.config;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.api.util.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Activate(group = Constants.PROVIDER)
public class ProducerTraceFilter implements Filter {

    Logger logger=LoggerFactory.getLogger(ProducerTraceFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext.getContext().setAttachment("sessionid",invocation.getAttachment("sessionid"));
        return invoker.invoke(invocation);
    }
}
