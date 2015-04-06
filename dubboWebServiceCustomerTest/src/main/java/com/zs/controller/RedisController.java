package com.zs.controller;

import com.zs.service._RedisService;
import com.zs.vo.MsgInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.*;

@Controller
@RequestMapping(value = "/redis")
public class RedisController {

    @Autowired
	private _RedisService redisService;
	
	@ResponseBody
	@RequestMapping(value = "/testRedis")
	public String testRedis() {
        System.out.println("RedisController -- testRedis()");

        if (redisService != null) {
            redisService.perpareData();                               //预装数据

            redisService.setValue("one", "aaa");                    //设置字符串

            Map<String,String> map = new HashMap<String, String>();    //设置Map
            map.put("letter", "abcd");
            map.put("number", "1234");
            redisService.setMap("letterAndNumber", map);

            List<String> pepleList = new ArrayList<String>();         //设置List
            pepleList.add("Jim");
            pepleList.add("Dave");
            pepleList.add("Bart");
            redisService.setList("peopleList", pepleList);

            System.out.println(redisService.getValue("one"));        //获取字符串
            Map<String, List<String>> resultMap = redisService.getMap("letterAndNumber");  //获取Map
            System.out.println(resultMap);
            List<String> resultList = redisService.getList("peopleList");   //获取List
            System.out.println(resultList);
            return "pages/success";
        }
		return "pages/error";
    }
}