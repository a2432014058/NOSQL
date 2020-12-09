package com.bjtu.redis.util;



import com.bjtu.redis.JedisInstance;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

public class Jedisutil {
    private static JedisPool jedisPool = JedisInstance.getInstance();

    public static void setIncrNum(String key , int field){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.incrBy(key,Long.parseLong(String.valueOf(field)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisPool.returnResource(jedis);
        }
    }

    public static String getValueNum(String key){
        String ret=null;
        Jedis jedis=null;
        try{
            jedis = jedisPool.getResource();
            ret = jedis.get(key);
        }catch(Exception e){

        }finally {
            jedisPool.returnResource(jedis);
        }
        return ret;
    }

    public static void setHashPush(String key,String field,String value){
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            jedis.hset(key, field, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisPool.returnResource(jedis);
        }
    }

    public static Map<String,String> getHashMap(String key){
        Jedis jedis = null;
        Map<String,String> ret = null;
        try {
            jedis = jedisPool.getResource();
            ret = jedis.hgetAll(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisPool.returnResource(jedis);
        }
        return ret;
    }


}

