package com.producer.practice;

public class Father {
    int x=10;

    public Father(){
        this.print();
        x=20;
    }

    public void print(){
        System.out.println("Father.x="+x);
    }
}

class mSon extends Father{
    int x=30;

    public mSon(){
        this.print();
        x=40;
    }

    @Override
    public void print() {
        System.out.println("son.x="+x);
    }

    public static void main(String[] args) {
        mSon f=new mSon();
        System.out.println(f.x);
    }
}
