package com.ipangoo.service.impl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.ipangoo.service.RedisService;

/**
 * @description
 *
 * @author libing
 * @created 2014年12月3日下午2:21:54
 * @version 1.0
 */
public class RedisServiceImpl implements RedisService {

	private Jedis jedis = null;
	
	public Jedis getRedis(String host, int port) {
		if(jedis == null){
			jedis = new Jedis(host,port);
		}
		return jedis;
	}

}
