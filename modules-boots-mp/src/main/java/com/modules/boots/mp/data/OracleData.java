/**
 * All rights Reserved, Designed By 林溪
 * Copyright:    Copyright(C) 2016-2030
 */

package com.modules.boots.mp.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * oracle基础数据配置
 * @author：林溪
 * @date：2020年10月2日
 */
@Configuration
@ConditionalOnProperty(prefix = "modules", name = "oracle.enabled", havingValue = "true")
@Data
public class OracleData {

    @Value("${modules.oracle.url}")
    private String url;

    @Value("${modules.oracle.username}")
    private String username;

    @Value("${modules.oracle.password}")
    private String password;

    @Value("${modules.oracle.jasypt.password}")
    private String jasyptPassword;

    @Value("${modules.oracle.model-path}")
    private String modelPath;

    @Value("${modules.oracle.mapper-path}")
    private String mapperPath;

    @Value("${modules.oracle.initial-size:1}")
    private int initialSize;

    @Value("${modules.oracle.max-active:30}")
    private int maxActive;

    @Value("${modules.oracle.min-idle:3}")
    private int minIdle;

    @Value("${modules.oracle.max-wait:60000}")
    private int maxWait;

    @Value("${modules.oracle.time-between-eviction-runs-millis:60000}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${modules.oracle.min-evictable-idle-time-millis:300000}")
    private int minEvictableIdleTimeMillis;

    @Value("${modules.oracle.validation-query:select 1 from dual}")
    private String validationQuery;

    @Value("${modules.oracle.test-while-idle:true}")
    private boolean testWhileIdle;

    @Value("${modules.oracle.test-on-borrow:false}")
    private boolean testOnBorrow;

    @Value("${modules.oracle.test-on-return:false}")
    private boolean testOnReturn;

    @Value("${modules.oracle.pool-prepared-statements:true}")
    private boolean poolPreparedStatements;

    @Value("${modules.oracle.max-pool-prepared-statement-per-connection-size:20}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${modules.oracle.filter-class-names:stat,slf4j}")
    private String filters;

    @Value("${modules.oracle.connection-properties:stat.mergeSql=true;stat.slowSqlMillis=5000}")
    private String connectionProperties = "";

}
