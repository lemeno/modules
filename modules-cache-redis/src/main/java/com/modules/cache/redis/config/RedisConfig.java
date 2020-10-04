/**
 * All rights Reserved, Designed By 林溪
 * Copyright:    Copyright(C) 2016-2030
 */

package com.modules.cache.redis.config;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;

/**
 * redis配置
 * @author：林溪
 * @date：2020年10月4日
 */
@Configuration
@EnableCaching
public class RedisConfig {

    /**
     * 配置redis客户端
     * @author 林溪
     * @param connectionFactory
     * @return RedisTemplate<String,Object>
     */
    @Bean(value = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory connectionFactory) {
        final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 获取连接工厂
        redisTemplate.setConnectionFactory(connectionFactory);
        // 采用Jackson替换默认的JDKSerialization
        final Jackson2JsonRedisSerializer<?> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        // 定义对象映射类，负责把对象映射成JSON
        final ObjectMapper objectMapper = new ObjectMapper();
        // 设置属性的可见为全部可见及全部生效
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 将类名称序列化到json串中
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        // 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 加载映射对象
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        // 序列化redis中的key的值为字符串
        final RedisSerializer<?> redisSerializer = new StringRedisSerializer();
        // 设置普通key值
        redisTemplate.setKeySerializer(redisSerializer);
        // 设置hashkey值
        redisTemplate.setHashKeySerializer(redisSerializer);
        // 设置普通value值
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // 设置hashvalue值
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 配置redis缓存策略
     * @author 林溪
     * @return CacheManager
     */
    @Bean
    public CacheManager cacheManager(JedisConnectionFactory connectionFactory) {
        final RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        // 采用Jackson替换默认的JDKSerialization
        final Jackson2JsonRedisSerializer<?> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        // 定义对象映射类，负责把对象映射成JSON
        final ObjectMapper objectMapper = new ObjectMapper();
        // 设置属性的可见为全部可见及全部生效
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 将类名称序列化到json串中
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        // 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 加载映射对象
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        // 配置序列化，解决乱码问题
        final RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                // 默认过期时间,0表示永久
                .entryTtl(Duration.ofMinutes(2))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .disableCachingNullValues();
        // 构建缓存管理器
        final RedisCacheManager cacheManager = RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .build();
        return cacheManager;
    }

}
