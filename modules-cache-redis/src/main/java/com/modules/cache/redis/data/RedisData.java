/**
 * All rights Reserved, Designed By 林溪
 * Copyright:    Copyright(C) 2016-2030
 */

package com.modules.cache.redis.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * redis常量
 * @author：林溪
 * @date：2020年10月5日
 */
@Configuration
public class RedisData {

    @Value("${modules.cache.redis.db:0}")
    private int db;

    @Value("${modules.cache.redis.host:127.0.0.1}")
    private String host;

    @Value("${modules.cache.redis.port:6379}")
    private Integer port;

    @Value("${modules.cache.redis.password}")
    private String password;

    @Value("${modules.cache.redis.maxIdle:300}")
    private Integer maxIdle;

    @Value("${modules.cache.redis.timeout:10000}")
    private Integer timeout;

    @Value("${modules.cache.redis.maxTotal:1000}")
    private Integer maxTotal;

    @Value("${modules.cache.redis.maxWaitMillis:1000}")
    private Integer maxWaitMillis;

    @Value("${modules.cache.redis.minEvictableIdleTimeMillis:300000}")
    private Integer minEvictableIdleTimeMillis;

    @Value("${modules.cache.redis.numTestsPerEvictionRun:1024}")
    private Integer numTestsPerEvictionRun;

    @Value("${modules.cache.redis.timeBetweenEvictionRunsMillis:30000}")
    private Long timeBetweenEvictionRunsMillis;

    @Value("${modules.cache.redis.testOnBorrow:true}")
    private Boolean testOnBorrow;

    @Value("${modules.cache.redis.testWhileIdle:true}")
    private Boolean testWhileIdle;

    @Value("${modules.cache.redis.cache-time-out:300}")
    private Integer cacheTimeOut;

    public int getDb() {
        return db;
    }

    public void setDb(int db) {
        this.db = db;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(Integer maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public Integer getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(Integer minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public Integer getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    public void setNumTestsPerEvictionRun(Integer numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    public Long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(Long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public Boolean getTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(Boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public Integer getCacheTimeOut() {
        return cacheTimeOut;
    }

    public void setCacheTimeOut(Integer cacheTimeOut) {
        this.cacheTimeOut = cacheTimeOut;
    }

}
