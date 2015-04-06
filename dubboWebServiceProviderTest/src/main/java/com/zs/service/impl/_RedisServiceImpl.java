package com.zs.service.impl;

import com.zs.service._RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;

@Service(value = "_redisServiceImpl")
public class _RedisServiceImpl implements _RedisService {
    @Autowired
    private JedisPool jedisPool;
    private Jedis jedis;

    //由于reids是缓存，使用本service的之前，先初始化一些数据，真实环境下，reids里面就有值，无须下面的代码
    @Override
    public void perpareData(){
        jedis = jedisPool.getResource();
        jedis.set("name", "zs");                                    // 放入3个String类型的key-value
        jedis.mset("a", "aaa", "b", "bbb");

        Map<String, String> user = new HashMap<String, String>();    //放入一个Map
        user.put("username", "root");
        user.put("password", "123456");
        jedis.hmset("user", user);

        jedis.lpush("java framework", "spring");                 // 放入一个List
        jedis.lpush("java framework", "struts");
        jedis.lpush("java framework", "hibernate");

        jedis.sadd("sname", "minxr");                             // 放入一个set
        jedis.sadd("sname", "jarorwar");
        jedis.sadd("sname", "闵晓荣");
        jedis.sadd("sanme", "noname");
        jedis.srem("sname", "noname");
    }
    @Override
    public boolean setValue(String key, String value){
        jedis = jedisPool.getResource();
        if(jedis != null){
            jedis.set(key, value);
            return true;
        }
        return false;
    }
    @Override
    public String getValue(String key){
        jedis = jedisPool.getResource();
        if(jedis != null){
            return jedis.get(key);
        }
        return null;
    }

    @Override
    public boolean setMap(String key, Map<String, String> map){
        jedis = jedisPool.getResource();
        if(jedis != null){
            jedis.hmset(key, map);
            return true;
        }
        return false;
    }
    @Override
    public Map<String, List<String>> getMap(String rediskey){
        Map<String, List<String>> map = new HashMap<String,List<String>>();
        jedis = jedisPool.getResource();
        if(jedis != null){
            Iterator<String> iter = jedis.hkeys(rediskey).iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                map.put(key, jedis.hmget(rediskey, key));
            }
            return map;
        }
        return null;
    }
    @Override
    public boolean setList(String key, List<String> list){
        jedis = jedisPool.getResource();
        jedis.del(key);                                                 //先删掉redis中key元素
        if(jedis != null){
            for(String item : list){
                jedis.lpush(key, item);
            }
            return true;
        }
        return false;
    }
    @Override
    public List<String> getList(String key){
        jedis = jedisPool.getResource();
        if(jedis != null){
            return jedis.lrange(key, 0, -1);
        }
        return null;
    }

}
