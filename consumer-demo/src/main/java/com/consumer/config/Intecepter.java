package com.consumer.config;

import com.alibaba.dubbo.rpc.RpcContext;
import com.api.util.HttpContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Intecepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        RpcContext.getContext().setAttachment("sessionid",request.getParameter("sessionid"));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        RpcContext.getContext().removeAttachment("sessionid");
    }
}
