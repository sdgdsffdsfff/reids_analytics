package com.ipangoo.redis;

import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * @description
 *
 * @author libing
 * @created 2014年12月3日下午2:24:49
 * @version 1.0
 */
public class RedisInfo {
	/**
	 * 得到所有的key集合
	 * @param jedis
	 * @return
	 */
	public Set<String> getRedisKeys(Jedis jedis){
		return jedis.keys("*");
	}
	
	
}
