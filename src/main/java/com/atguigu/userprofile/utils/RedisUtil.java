package com.atguigu.userprofile.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RedisUtil {



    public static String redisHost;

    public  static Integer redisPort;

    @Value("${spring.redis.host}")
    public void setRedisHost(String redisHost){
        RedisUtil.redisHost=redisHost;
    }
    @Value("${spring.redis.port}")
    public void setRedisPort(String redisPort){
        RedisUtil.redisPort=Integer.valueOf(redisPort);
    }

    private static JedisPool jedisPool=null;

    public static Jedis getJedisFromPool(){
        if(jedisPool==null){
            JedisPoolConfig jedisPoolConfig =new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(200); //最大可用连接数
            jedisPoolConfig.setMaxIdle(50); //最大闲置连接数
            jedisPoolConfig.setMinIdle(20); //最小闲置连接数
            jedisPoolConfig.setBlockWhenExhausted(true); //连接耗尽是否等待
            jedisPoolConfig.setMaxWaitMillis(2000); //等待时间
            jedisPoolConfig.setTestOnBorrow(true); //取连接的时候进行一下测试 ping pong

            jedisPool=new JedisPool(jedisPoolConfig,redisHost, Integer.valueOf(redisPort) );
            return jedisPool.getResource();
        }else{
            return jedisPool.getResource();
        }
    }


    //展开 bitmap为数组方法
    //  8倍数数据展开存在问题
    public static List<Integer> getBitmapToArray(byte[] bitMapBytes){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i <bitMapBytes.length ; i++) {
            for (int j = 0; j < 8; j++) {
                if ((bitMapBytes[i] & (1 << j)) == (1 << j)) { //8位逐个判断哪一位是1
                    System.out.println("num:" + ((8 * i) + 8 - (j + 1)));
                    Integer num = ((8 * i) + 8 - (j + 1));
                    list.add(num);
                }
            }
        }
        return list;
    }


}
