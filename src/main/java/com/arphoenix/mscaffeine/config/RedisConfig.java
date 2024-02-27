package com.arphoenix.mscaffeine.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * Classe {@link org.springframework.context.annotation.Configuration} para implementar o AUHT com o REDIS
 * 
 * @author rsdelia
 *
 */
@Configuration
public class RedisConfig {

	@Value("${spring.redis.host}")
	private String redisHost;

	@Value("${spring.redis.port}")
	private int redisPort;

	@Value("${spring.redis.password}")
	private String redisPassword;

	@Value("${spring.redis.ssl}")
	private boolean useSsl;

	@Value("${spring.redis.timeout}")
	private long redisTimeout;

	@SuppressWarnings("deprecation")
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisHost, redisPort);
		lettuceConnectionFactory.setPassword(redisPassword);
		lettuceConnectionFactory.setUseSsl(useSsl);
		lettuceConnectionFactory.setTimeout(redisTimeout);
		return lettuceConnectionFactory;
	}
}