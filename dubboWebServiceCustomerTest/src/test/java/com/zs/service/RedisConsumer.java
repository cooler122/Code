package com.zs.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

public class RedisConsumer {
    public static void main(String args[]){
        String configLocation= "spring/spring-service-consumer.xml";
        ApplicationContext context =new ClassPathXmlApplicationContext(configLocation);
        _RedisService redisService = (_RedisService) context.getBean("redisService");

        if (redisService != null) {
            redisService.perpareData();                               //预装数据

            redisService.setValue("one", "aaa");                    //设置字符串

            Map<String,String> map = new HashMap<String, String>();  //设置Map
            map.put("letter", "abcd");
            map.put("number", "1234");
            redisService.setMap("letterAndNumber", map);

            List<String> pepleList = new ArrayList<String>();        //设置List
            pepleList.add("Jim");
            pepleList.add("Dave");
            pepleList.add("Bart");
            redisService.setList("peopleList", pepleList);

            System.out.println(redisService.getValue("one"));        //获取字符串
            Map<String, List<String>> resultMap = redisService.getMap("letterAndNumber");  //获取Map
            System.out.println(resultMap);
            List<String> resultList = redisService.getList("peopleList");   //获取List
            System.out.println(resultList);
        }
    }
}
