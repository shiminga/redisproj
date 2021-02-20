package com.producer.practice;

import java.security.PublicKey;
import java.util.LinkedList;
import java.util.List;

public class PublishAndSubcribe {

    List<Subcriber> subcribers=new LinkedList<>();

    public void subcribe(Subcriber subcriber){
        this.subcribers.add(subcriber);
    }

    public void unsubcribe(Subcriber subcriber){
        this.subcribers.remove(subcriber);
    }

    public void pulish(Publisher publisher,String msg){
        for(Subcriber subcriber:subcribers){
            subcriber.listen(publisher,msg);
        }
    }
}

class Publisher{
    String name;

    Publisher(String name){
        this.name=name;
    }

    public void pulish(PublishAndSubcribe pulishAndSubcribe,String msg){
        pulishAndSubcribe.pulish(this,msg);
    }
}
class Subcriber{
    String name;

    Subcriber(String name){
        this.name=name;
    }

    public void listen(Publisher publisher, String msg){
        System.out.println(this.name+"收到"+publisher.name+"发送的"+msg);
    }

    public static void main(String[] args) {
        PublishAndSubcribe publishAndSubcribe=new PublishAndSubcribe();

        Publisher p1=new Publisher("发布者1");
        Publisher p2=new Publisher("发布者2");
        Publisher p3=new Publisher("发布者3");

        Subcriber s1=new Subcriber("订阅者1");
        Subcriber s2=new Subcriber("订阅者2");
        Subcriber s3=new Subcriber("订阅者3");
        publishAndSubcribe.subcribe(s1);
        publishAndSubcribe.subcribe(s2);
        publishAndSubcribe.subcribe(s3);

        p1.pulish(publishAndSubcribe,"1号发来的消息");
        p2.pulish(publishAndSubcribe,"2号发来的消息");
        p3.pulish(publishAndSubcribe,"3号发来的消息");
    }
}
