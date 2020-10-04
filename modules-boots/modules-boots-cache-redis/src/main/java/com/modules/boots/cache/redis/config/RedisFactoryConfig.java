/**
 * All rights Reserved, Designed By 林溪
 * Copyright:    Copyright(C) 2016-2030
 */

package com.modules.boots.cache.redis.config;

import java.time.Duration;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis工厂配置
 * @author：林溪
 * @date：2020年10月4日
 */
@Configuration
@ConfigurationProperties(prefix = "modules.cache.redis")
public class RedisFactoryConfig {

    @Value("${db:0}")
    private int db;

    @Value("${host:127.0.0.1}")
    private String host;

    @Value("${port:6379}")
    private Integer port;

    @Value("${password}")
    private String password;

    @Value("${maxIdle:300}")
    private Integer maxIdle;

    @Value("${timeout:10000}")
    private Integer timeout;

    @Value("${maxTotal:1000}")
    private Integer maxTotal;

    @Value("${maxWaitMillis:1000}")
    private Integer maxWaitMillis;

    @Value("${minEvictableIdleTimeMillis:300000}")
    private Integer minEvictableIdleTimeMillis;

    @Value("${numTestsPerEvictionRun:1024}")
    private Integer numTestsPerEvictionRun;

    @Value("${timeBetweenEvictionRunsMillis:30000}")
    private Long timeBetweenEvictionRunsMillis;

    @Value("${testOnBorrow:true}")
    private Boolean testOnBorrow;

    @Value("${testWhileIdle:true}")
    private Boolean testWhileIdle;

    /**
     * 配置jedis连接工厂
     * @author 林溪
     * @return JedisConnectionFactory
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        // 配置数据库索引
        config.setDatabase(db);
        // 配置连接地址
        config.setHostName(host);
        // 配置端口号
        config.setPort(port);
        // 配置密码
        if (Strings.isNotEmpty(password)) {
            config.setPassword(RedisPassword.of(password));
        }
        // 构建连接池构造器
        final JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder)
                JedisClientConfiguration.builder().connectTimeout(Duration.ofMillis(timeout));
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
        pool.setMaxIdle(maxIdle);
        // 连接池的最大数据库连接数。设为0表示无限制,如果是jedis 2.4以后用redis.maxTotal
        pool.setMaxTotal(maxTotal);
        // 最大建立连接等待时间。如果超过此时间将接到异常。设为-1表示无限制。
        pool.setMaxWaitMillis(maxWaitMillis);
        // 连接的最小空闲时间 默认1800000毫秒(30分钟)
        pool.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        // 每次释放连接的最大数目,默认3
        pool.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        pool.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
        pool.setTestOnBorrow(testOnBorrow);
        // 在空闲时检查有效性, 默认false
        pool.setTestWhileIdle(testWhileIdle);
        return pool;
    }

}
