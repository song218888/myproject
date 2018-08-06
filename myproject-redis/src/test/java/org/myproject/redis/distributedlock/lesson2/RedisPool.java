package org.myproject.redis.distributedlock.lesson2;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {
	private static JedisPool pool;
	
	private static int maxTotal = 20;
	
	private static int maxIdle = 10;

	private static int minIdle = 5;

	private static boolean testOnBorrow = true;
	
	private static boolean testOnReturn = false;
	
	static{
		initPool();
	}
	
	public static Jedis getJedis(){
		return pool.getResource();
	}
	
	
	private static void initPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(maxTotal);
		config.setMaxIdle(maxIdle);
		config.setMinIdle(minIdle);
		config.setTestOnBorrow(testOnBorrow);
		config.setTestOnReturn(testOnReturn);
		config.setBlockWhenExhausted(true);
		pool = new JedisPool(config , "192.168.18.103",6379, 5000, "liqiyao");
		
	}


	public static void close(Jedis jedis){
		jedis.close();
	}
}
