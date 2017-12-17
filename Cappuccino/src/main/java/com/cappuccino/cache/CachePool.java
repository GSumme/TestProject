package com.cappuccino.cache;

import java.util.Properties;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class CachePool {

	private static final Logger logger = Logger.getLogger(CachePool.class);

	JedisPool pool;
	private static final CachePool cachePool = new CachePool();

	public static CachePool getInstance() {
		return cachePool;
	}

	private CachePool() {
		Properties props = new Properties();
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		try {
			props.load(classloader.getResourceAsStream("redis.properties"));
			// jedis池配置
			JedisPoolConfig config = new JedisPoolConfig();
			String host = props.getProperty("host");
			Integer port = Integer.parseInt(props.getProperty("port").toString());
			pool = new JedisPool(config, host, port);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public Jedis getJedis() {
		Jedis jedis = null;
		boolean borrowOrOprSuccess = true;
		try {
			jedis = pool.getResource();
		} catch (JedisConnectionException e) {
			borrowOrOprSuccess = false;
			if (jedis != null)
				pool.returnBrokenResource(jedis);
		} finally {
			if (borrowOrOprSuccess)
				pool.returnResource(jedis);
		}
		jedis = pool.getResource();
		return jedis;
	}

	public JedisPool getJedisPool() {
		return this.pool;
	}

}
