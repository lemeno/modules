/**
 * All rights Reserved, Designed By 林溪
 * Copyright:    Copyright(C) 2016-2030
 */

package com.modules.cache.redis.config;

import java.time.Duration;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import com.modules.cache.redis.data.RedisData;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis工厂配置
 * @author：林溪
 * @date：2020年10月4日
 */
@Configuration
public class RedisFactoryConfig {

    @Autowired
    private RedisData redisData;

    /**
     * 配置jedis连接工厂
     * @author 林溪
     * @return JedisConnectionFactory
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        // 配置数据库索引
        config.setDatabase(redisData.getDb());
        // 配置连接地址
        config.setHostName(redisData.getHost());
        // 配置端口号
        config.setPort(redisData.getPort());
        // 配置密码
        if (Strings.isNotEmpty(redisData.getPassword())) {
            config.setPassword(RedisPassword.of(redisData.getPassword()));
        }
        // 构建连接池构造器
        final JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder()
                .connectTimeout(Duration.ofSeconds(redisData.getTimeout()));
        // 指定jedisPoolConifig来修改默认的连接池构造器
        jpcb.poolConfig(jedisPoolConfig);
        // 通过构造器来构造jedis客户端配置
        final JedisClientConfiguration jedisClientConfiguration = jpcb.build();
        // 单机配置 + 客户端配置 = jedis连接工厂
        return new JedisConnectionFactory(config, jedisClientConfiguration);

    }

    /**
     * 配置jedis连接池
     * @author 林溪
     * @return JedisPoolConfig
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        final JedisPoolConfig pool = new JedisPoolConfig();
        // 最大空闲数
        pool.setMaxIdle(redisData.getMaxIdle());
        // 连接池的最大数据库连接数。设为0表示无限制,如果是jedis 2.4以后用redis.maxTotal
        pool.setMaxTotal(redisData.getMaxTotal());
        // 最大建立连接等待时间。如果超过此时间将接到异常。设为-1表示无限制。
        pool.setMaxWaitMillis(redisData.getMaxWaitMillis());
        // 连接的最小空闲时间 默认1800000毫秒(30分钟)
        pool.setMinEvictableIdleTimeMillis(redisData.getMinEvictableIdleTimeMillis());
        // 每次释放连接的最大数目,默认3
        pool.setNumTestsPerEvictionRun(redisData.getNumTestsPerEvictionRun());
        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        pool.setTimeBetweenEvictionRunsMillis(redisData.getTimeBetweenEvictionRunsMillis());
        // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
        pool.setTestOnBorrow(redisData.getTestOnBorrow());
        // 在空闲时检查有效性, 默认false
        pool.setTestWhileIdle(redisData.getTestWhileIdle());
        return pool;
    }

}
