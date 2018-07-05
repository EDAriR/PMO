package com.syntrontech.pmo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisPoolConfig {
		
	@Value("${redis.pool.max-total}")
	private String maxTotal;
	
	@Value("${redis.pool.max-wait-millis}")
	private String maxWaitMillis;
	
	@Value("${redis.pool.min-idle}")
	private String minIdle;
	
	@Value("${redis.pool.max-idle}")
	private String maxIdle;
	
	@Value("${redis.pool.test-on-borrow}")
	private String testOnBorrow;
	
	@Value("${redis.host}")
	private String host;

	@Value("${redis.port}")
	private String port;
	
	@Bean
	public JedisPool getJedisPool(){
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(Integer.parseInt(maxTotal));
		config.setMaxWaitMillis(Long.parseLong(maxWaitMillis));
		config.setMinIdle(Integer.parseInt(minIdle));
		config.setMaxIdle(Integer.parseInt(maxIdle));
		config.setTestOnBorrow(Boolean.parseBoolean(testOnBorrow));
		
		return new JedisPool(config, host, Integer.parseInt(port));
	}
}
