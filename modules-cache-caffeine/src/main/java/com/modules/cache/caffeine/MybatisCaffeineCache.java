/**
 * All rights Reserved, Designed By 林溪
 * Copyright:    Copyright(C) 2016-2030
 */

package com.modules.cache.caffeine;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.benmanes.caffeine.cache.Cache;
import com.modules.cache.caffeine.utils.SpringUtils;

/**
 * mybatis本地缓存
 * @author：林溪
 * @date：2020年10月7日
 */
@SuppressWarnings("unchecked")
public class MybatisCaffeineCache implements org.apache.ibatis.cache.Cache{

    @Autowired
    private Cache<String, Object> caffeineCache;

    // 读写锁
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    private final String id;

    public MybatisCaffeineCache(final String id) {
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
        if (caffeineCache == null) {
            // MybatisRedisCache没有注入，采用手动获取caffeineCache对象
            caffeineCache = (Cache<String, Object>) SpringUtils.getBean("caffeineCache");
        }
        caffeineCache.put(key.toString(), value);
    }

    @Override
    public Object getObject(Object key) {
        if (caffeineCache == null) {
            // MybatisRedisCache没有注入，采用手动获取caffeineCache对象
            caffeineCache = (Cache<String, Object>) SpringUtils.getBean("caffeineCache");
        }
        return caffeineCache.getIfPresent(key.toString());
    }

    @Override
    public Object removeObject(Object key) {
        if (caffeineCache == null) {
            // MybatisRedisCache没有注入，采用手动获取caffeineCache对象
            caffeineCache = (Cache<String, Object>) SpringUtils.getBean("caffeineCache");
        }
        return caffeineCache.asMap().remove(key.toString());
    }

    @Override
    public void clear() {
        if (caffeineCache == null) {
            // MybatisRedisCache没有注入，采用手动获取caffeineCache对象
            caffeineCache = (Cache<String, Object>) SpringUtils.getBean("caffeineCache");
        }
        caffeineCache.cleanUp();
    }

    @Override
    public int getSize() {
        if (caffeineCache == null) {
            // MybatisRedisCache没有注入，采用手动获取caffeineCache对象
            caffeineCache = (Cache<String, Object>) SpringUtils.getBean("caffeineCache");
        }
        final Long size = caffeineCache.estimatedSize();
        return size.intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

}
