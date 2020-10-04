/**
 * All rights Reserved, Designed By 林溪
 * Copyright:    Copyright(C) 2016-2030
 */

package com.modules.boots.db.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * mysql基础数据配置
 * @author：林溪
 * @date：2020年10月2日
 */
@Configuration
@ConditionalOnProperty(prefix = "modules", name = "mysql.enabled", havingValue = "true")
@Data
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

}
