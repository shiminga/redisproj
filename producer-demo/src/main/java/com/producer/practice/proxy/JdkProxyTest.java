package com.producer.practice.proxy;



import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyTest implements InvocationHandler {
    Object target;
    JdkProxyTest(Object target){
        this.target=target;
    }

    public Object newProxy(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用前");
        Object result=method.invoke(target,args);
        System.out.println("调用后");
        return result;
    }

    public static void main(String[] args) {
        Person p=new Person();
        Subject subject=(Subject) new JdkProxyTest(p).newProxy();
        subject.doSomeThing();
        subject.saySomthing();
    }
}

interface  Subject{
    void doSomeThing();

    void saySomthing();
}

class Person implements Subject{

    @Override
    public void doSomeThing() {
        System.out.println("某人做事...");
    }

    @Override
    public void saySomthing() {
        System.out.println("某人说话...");
    }
}
