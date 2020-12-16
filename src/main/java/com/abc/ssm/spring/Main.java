package com.abc.ssm.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx =new ClassPathXmlApplicationContext("applicationContext.xml");
        Person zhao = (Person) ctx.getBean("zhao");
        Phone phone = zhao.getPhone();
        System.out.println(zhao.getPname());
        System.out.println(phone);



    }
}