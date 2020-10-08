/**
 * All rights Reserved, Designed By 林溪
 * Copyright:    Copyright(C) 2016-2030
 */

package com.modules.cache.redis.cache;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import com.modules.cache.redis.data.RedisData;
import com.modules.cache.redis.utils.SpringUtils;

/**
 * redis缓存器
 * @author：林溪
 * @date：2020年10月4日
 */
@SuppressWarnings("unchecked")
public class MybatisRedisCache implements Cache {

    // 读写锁
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    // 这里使用了redis缓存，使用springboot自动注入
    private RedisTemplate<String, Object> redisTemplate;

    private RedisData redisData;

    private String id;

    public MybatisRedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        if (redisTemplate == null) {
            // MybatisRedisCache没有注入，采用手动获取restTemplate对象
            redisTemplate = (RedisTemplate<String, Object>) SpringUtils.getBean("redisTemplate");
        }
        if (value != null) {
            if (redisData == null) {
                redisData = (RedisData) SpringUtils.getBean("redisData");
            }
            redisTemplate.opsForValue().set(key.toString(), value, Duration.ofSeconds(redisData.getCacheTimeOut()));
        }

    }

    @Override
    public Object getObject(Object key) {
        if (redisTemplate == null) {
            // MybatisRedisCache没有注入，采用手动获取restTemplate对象
            redisTemplate = (RedisTemplate<String, Object>) SpringUtils.getBean("redisTemplate");
        }
        if (key != null) {
            if (redisData == null) {
                redisData = (RedisData) SpringUtils.getBean("redisData");
            }
            redisTemplate.expire(key.toString(), redisData.getCacheTimeOut(), TimeUnit.SECONDS);
            return redisTemplate.opsForValue().get(key.toString());
        } else {
            return null;
        }
    }

    @Override
    public Object removeObject(Object key) {
        if (redisTemplate == null) {
            // MybatisRedisCache没有注入，采用手动获取restTemplate对象
            redisTemplate = (RedisTemplate<String, Object>) SpringUtils.getBean("redisTemplate");
        }
        if (key != null) {
            redisTemplate.delete(key.toString());
        }
        return null;

    }

    @Override
    public void clear() {
        if (redisTemplate == null) {
            redisTemplate = (RedisTemplate<String, Object>) SpringUtils.getBean("redisTemplate");
        }
        final Set<String> keys = redisTemplate.keys("*:" + id + "*");
        if (!CollectionUtils.isEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }

    @Override
    public int getSize() {
        if (redisTemplate == null) {
            // MybatisRedisCache没有注入，采用手动获取restTemplate对象
            redisTemplate = (RedisTemplate<String, Object>) SpringUtils.getBean("redisTemplate");
        }
        final Long size = redisTemplate.execute((RedisCallback<Long>) RedisServerCommands::dbSize);
        return size.intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
}
