package com.boco.eoms.workbench.infopub.test.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunDemo {

    public static void kwikEMart() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext-aop.xml");

        //如果你想通过类来引用这个的话,就要用到CGLIB.jar了,同时在代理工厂里面设置:
        //<property name="proxyTargetClass" value="true" />
        KwikEMart akem = (KwikEMart) context.getBean("kwikEMart");

        akem.buySquish(new Customer());
        akem.buyPepper(new Customer());
        akem.buyCheese(new Customer());

    }

    public static void main(String[] args) {
        kwikEMart();
    }

}
