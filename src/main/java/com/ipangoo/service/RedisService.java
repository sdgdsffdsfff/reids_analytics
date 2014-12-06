package com.ipangoo.service;

import redis.clients.jedis.Jedis;

/**
 * @description redis服务类
 * 
 * @author libing
 * @created 2014年12月3日下午2:02:21
 * @version 1.0
 */
public interface RedisService {
	public Jedis getRedis(String url, int port);
}
