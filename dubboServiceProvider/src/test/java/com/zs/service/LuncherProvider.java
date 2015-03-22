package com.zs.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LuncherProvider {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo-provider.xml");
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
        Thread.sleep(1000 * 60 * 1);                 //此设置这个dubbo服务开启多长时间。
    }
}
