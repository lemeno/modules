/**
 * All rights Reserved, Designed By 林溪
 * Copyright:    Copyright(C) 2016-2030
 */

package com.modules.boots.mp.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author：林溪
 * @date：2020年10月2日
 */
@Configuration
@Data
public class MysqlData {

    @Value("${modules.mp.mysql.datasource.url}")
    private String url;

    @Value("${modules.mp.mysql.datasource.username}")
    private String username;

    @Value("${modules.mp.mysql.datasource.password}")
    private String password;

    @Value("${modules.mp.mysql.datasource.initial-size:1}")
    private int initialSize;

    @Value("${modules.mp.mysql.datasource.max-active:30}")
    private int maxActive;

    @Value("${modules.mp.mysql.datasource.min-idle:3}")
    private int minIdle;

    @Value("${modules.mp.mysql.datasource.max-wait:60000}")
    private int maxWait;

    @Value("${modules.mp.mysql.datasource.time-between-eviction-runs-millis:60000}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${modules.mp.mysql.datasource.min-evictable-idle-time-millis:300000}")
    private int minEvictableIdleTimeMillis;

    @Value("${modules.mp.mysql.datasource.validation-query:select 'x'}")
    private String validationQuery;

    @Value("${modules.mp.mysql.datasource.test-while-idle:true}")
    private boolean testWhileIdle;

    @Value("${modules.mp.mysql.datasource.test-on-borrow:false}")
    private boolean testOnBorrow;

    @Value("${modules.mp.mysql.datasource.test-on-return:false}")
    private boolean testOnReturn;

    @Value("${modules.mp.mysql.datasource.pool-prepared-statements:true}")
    private boolean poolPreparedStatements;

    @Value("${modules.mp.mysql.datasource.max-pool-prepared-statement-per-connection-size:20}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${modules.mp.mysql.datasource.filter-class-names:stat,slf4j}")
    private String filters;

    @Value("${modules.mp.mysql.datasource.connection-properties:stat.mergeSql=true;stat.slowSqlMillis=5000}")
    private String connectionProperties = "";

    @Value("${modules.mp.mysql.jasypt.password}")
    private String jasyptPassword;
}
