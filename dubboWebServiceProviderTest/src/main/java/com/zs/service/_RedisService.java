package com.zs.service;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public interface _RedisService {
    /**
     * 预装数据
     */
    public void perpareData();

    /**
     * 设置一个value为字符串的键值对
     * @param key
     * @param value
     * @return
     */
    public boolean setValue(String key, String value);

    /**
     * 由key获取一个字符串
     * @param key
     * @return
     */
    public String getValue(String key);

    /**
     * 设置一个Map
     * @param key
     * @param map
     * @return
     */
    public boolean setMap(String key, Map<String, String> map);

    /**
     * 获取Map
     * @param rediskey
     * @return
     */
    public Map getMap(String rediskey);

    /**
     * 设置一个list
     * @param key
     * @param list
     * @return
     */
    public boolean setList(String key, List<String> list);

    /**
     * 获取list
     * @param key
     * @return
     */
    public List<String> getList(String key);

}
