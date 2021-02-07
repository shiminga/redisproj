package com.shiming.redisproj.practice;


public class Employee {
    String name;

    int age;

    String department;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", department='" + department + '\'' +
                '}';
    }

    public static class Builder{

        Employee employee=new Employee();

        public Builder setName(String name) {
            employee.name = name;
            return this;
        }

        public Builder setAge(int age) {
            employee.age = age;
            return this;
        }

        public Builder setDepartment(String department) {
            employee.department = department;
            return this;
        }

        public Employee build(){
            return employee;
        }
    }

    public static void main(String[] args) {
        System.out.println(myPow(99,
                2100));
    }


    public static double myPow(double x, int n) {
        if(n==0){return 1d;}
        if(n==1){return x;}
        if(n==-1){return 1/x;}

        double half=myPow(x,n/2);
        double mod=myPow(x,n%2);
        return half*half*mod;
    }

}
