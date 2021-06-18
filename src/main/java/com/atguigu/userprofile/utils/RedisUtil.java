package com.atguigu.userprofile.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {


    public static void main(String[] args) {
        Jedis jedis =  RedisUtil.getJedis();


        jedis.set("k10000","v10000");

        String value = jedis.get("k10000");
        System.out.println(value);
        System.out.println(jedis.keys("*"));

        jedis.close();

    }


    static JedisPool jedisPool=null;


    public static Jedis  getJedis(){
        if(jedisPool==null){
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(100);
            jedisPoolConfig.setMinIdle(20);
            jedisPoolConfig.setMaxIdle(40);
            jedisPoolConfig.setBlockWhenExhausted(true);
            jedisPoolConfig.setMaxWaitMillis(1000);
            jedisPoolConfig.setTestOnBorrow(true);

            jedisPool=new JedisPool(jedisPoolConfig,"bigdata01",6379);
            return jedisPool.getResource();
        }else{
            return jedisPool.getResource();
        }

    }


}
