package com.ipangoo.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import redis.clients.jedis.Jedis;

import com.ipangoo.service.RedisService;
import com.ipangoo.service.impl.RedisServiceImpl;

/**
 * @description
 * 
 * @author libing
 * @created 2014年12月3日下午2:17:32
 * @version 1.0
 */
public class Test {
	public static void main(String[] args) throws IOException {
		RedisService redisService = new RedisServiceImpl();
		String host = args[0];
		int port = Integer.valueOf(args[1]);
		String fileName = args[2];
		BufferedWriter bf = null;
		try {
			bf = new BufferedWriter(new FileWriter(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Jedis jedis = redisService.getRedis(host, port);
		String infoRedis = jedis.info();
		bf.write(infoRedis + "\n");

		// 先选择库
		for (int i = 0; i < 3; i++) {
			jedis.select(i);

			Set<String> set = jedis.keys("*");

			Iterator<String> iter = set.iterator();

			while (iter.hasNext()) {
				Long len = 0l;
				String type = null;
				String key = null;
				key = iter.next();
				type = jedis.type(key);
				if (type.equals("list")) {
					len = jedis.llen(key);
				} else if (type.equals("hash")) {
					len = jedis.hlen(key);
				} else if (type.equals("set")) {
					len = jedis.scard(key);
				} else if (type.equals("zset")) {
					len = jedis.zcard(key);
				} else if (type.equals("string")) {
					len = 0l;
				}
				bf.write(i+"\t"+key + "\t" + type + "\t" + len + "\n");
			}
		}
		bf.flush();
		bf.close();
	}

}
