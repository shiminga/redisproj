package com.producer.practice.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyTest implements MethodInterceptor {
    Object target;

    Object bind(Object target){
        this.target=target;
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("调用前。。。");
        Object result=method.invoke(target,objects);
        System.out.println("调用后。。。");
        return result;
    }

    public static void main(String[] args) {
        Person p=new Person();
        CglibProxyTest cglibProxyTest=new CglibProxyTest();
        Person proxyObj=(Person) cglibProxyTest.bind(p);
        proxyObj.doSomeThing();
        proxyObj.saySomthing();
    }
}
