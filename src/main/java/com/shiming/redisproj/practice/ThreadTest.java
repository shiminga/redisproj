package com.shiming.redisproj.practice;

public class ThreadTest {

    static ThreadLocal<Person> tl=new ThreadLocal<>();
    public static void main(String[] args) throws Exception{

        Person person=new Person("mingo",25);
        Thread t1=new Thread(()->{
            tl.set(person);
            System.out.println(Thread.currentThread().getName()+tl.get());
            try {
                Thread.sleep(4000);
            }catch (Exception e){

            }
            Person p1=tl.get();
            p1.setName("Bob");
            System.out.println(Thread.currentThread().getName()+tl.get());
        },"t1");
        t1.start();

        Thread t2=new Thread(()->{

            tl.set(person);
            System.out.println(Thread.currentThread().getName()+tl.get());

            try {
                Thread.sleep(5000);
            }catch (Exception e){

            }
            System.out.println(Thread.currentThread().getName()+tl.get());
        },"t2");
        t2.start();

    }


    static class Person{
        String name;

        Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}
