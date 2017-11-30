package com.zdjy.bigdata.antun.redis;

import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
@Configuration
public class RedisConfig extends CachingConfigurerSupport{
	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object arg0, Method arg1, Object... arg2) {
				StringBuilder stringBuilder=new StringBuilder();
				stringBuilder.append(arg0.getClass().getSimpleName()+"_");
				stringBuilder.append(arg1.getName());
				if(arg2.length==0)
					return stringBuilder.toString();
				stringBuilder.append(":");
				int i=0;
				for(Object object:arg2) {
					i++;
					stringBuilder.append(object.toString());
					if(i<arg2.length)
						stringBuilder.append("_");
				}
				return stringBuilder.toString();
			}
		};
	}
	
	//配置spring boot缓存管理器---使用redis
	@Bean
	public CacheManager cacheManager(RedisTemplate<String,Object> redisTemplate) {
		RedisCacheManager redisCacheManager=new RedisCacheManager(redisTemplate);
		//设置缓存过期时间
//        redisCacheManager.setDefaultExpiration(60);//秒
		return redisCacheManager;
	}
	
	//配置redis
	@Bean
	public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new RedisObjectSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new RedisObjectSerializer());
		return redisTemplate;
	}
	
}
