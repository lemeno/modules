/**
 * All rights Reserved, Designed By 林溪
 * Copyright:    Copyright(C) 2016-2030
 */

package com.modules.db.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * mysql基础数据配置
 * @author：林溪
 * @date：2020年10月2日
 */
@Configuration
@ConditionalOnProperty(prefix = "modules", name = "mysql.enabled", havingValue = "true")
public class MysqlData {

    @Value("${modules.mysql.url}")
    private String url;

    @Value("${modules.mysql.username}")
    private String username;

    @Value("${modules.mysql.password}")
    private String password;

    @Value("${modules.mysql.jasypt.password}")
    private String jasyptPassword;

    @Value("${modules.mysql.model-path}")
    private String modelPath;

    @Value("${modules.mysql.mapper-path}")
    private String mapperPath;

    @Value("${modules.mysql.initial-size:1}")
    private int initialSize;

    @Value("${modules.mysql.max-active:30}")
    private int maxActive;

    @Value("${modules.mysql.min-idle:3}")
    private int minIdle;

    @Value("${modules.mysql.max-wait:60000}")
    private int maxWait;

    @Value("${modules.mysql.time-between-eviction-runs-millis:60000}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${modules.mysql.min-evictable-idle-time-millis:300000}")
    private int minEvictableIdleTimeMillis;

    @Value("${modules.mysql.validation-query:select 'x'}")
    private String validationQuery;

    @Value("${modules.mysql.test-while-idle:true}")
    private boolean testWhileIdle;

    @Value("${modules.mysql.test-on-borrow:false}")
    private boolean testOnBorrow;

    @Value("${modules.mysql.test-on-return:false}")
    private boolean testOnReturn;

    @Value("${modules.mysql.pool-prepared-statements:true}")
    private boolean poolPreparedStatements;

    @Value("${modules.mysql.max-pool-prepared-statement-per-connection-size:20}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${modules.mysql.filter-class-names:stat,slf4j}")
    private String filters;

    @Value("${modules.mysql.connection-properties:stat.mergeSql=true;stat.slowSqlMillis=5000}")
    private String connectionProperties = "";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJasyptPassword() {
        return jasyptPassword;
    }

    public void setJasyptPassword(String jasyptPassword) {
        this.jasyptPassword = jasyptPassword;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    public String getMapperPath() {
        return mapperPath;
    }

    public void setMapperPath(String mapperPath) {
        this.mapperPath = mapperPath;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public int getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public int getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public boolean isPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public void setPoolPreparedStatements(boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }

    public int getMaxPoolPreparedStatementPerConnectionSize() {
        return maxPoolPreparedStatementPerConnectionSize;
    }

    public void setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {
        this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public String getConnectionProperties() {
        return connectionProperties;
    }

    public void setConnectionProperties(String connectionProperties) {
        this.connectionProperties = connectionProperties;
    }



}
