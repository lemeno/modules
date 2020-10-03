/**
 * All rights Reserved, Designed By 林溪
 * Copyright:    Copyright(C) 2016-2030
 */

package com.modules.boots.mp.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.modules.bases.security.JasyptUtils;
import com.modules.boots.mp.data.OracleData;

import lombok.extern.slf4j.Slf4j;

/**
 * oracle数据源配置
 * @author：林溪
 * @date：2020年10月2日
 */
@ConditionalOnProperty(prefix = "modules", name = "oracle.enabled", havingValue = "true")
@ServletComponentScan
@Configuration
@Slf4j
public class OracleDataSource {

    @Autowired
    private OracleData oracleData;

    /**
     * 数据源配置
     * @author 林溪
     * @return DataSource
     */
    @Bean(name = "oracleDataSource", initMethod = "init", destroyMethod = "close")
    @Qualifier("oracleDataSource")
    @Primary
    public DataSource oracleDataSource() {
        DruidDataSource datasource = null;
        try {
            datasource = new DruidDataSource();
            datasource.setDriverClassName("com.p6spy.engine.spy.P6SpyDriver");
            datasource.setUrl(oracleData.getUrl());
            datasource.setUsername(oracleData.getUsername());
            datasource.setPassword(JasyptUtils.decryptMsg(oracleData.getJasyptPassword(), oracleData.getPassword()));
            // 初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时
            datasource.setInitialSize(oracleData.getInitialSize());
            // 最小连接池数量
            datasource.setMinIdle(oracleData.getMinIdle());
            // 最大连接池数量
            datasource.setMaxActive(oracleData.getMaxActive());
            // 配置获取连接等待超时的时间
            datasource.setMaxWait(oracleData.getMaxWait());
            // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
            datasource.setTimeBetweenEvictionRunsMillis(oracleData.getTimeBetweenEvictionRunsMillis());
            // 配置一个连接在池中最小生存的时间，单位是毫秒
            datasource.setMinEvictableIdleTimeMillis(oracleData.getMinEvictableIdleTimeMillis());
            // 用来检测连接是否有效的sql，要求是一个查询语句，常用select 'x'.
            // 如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。
            datasource.setValidationQuery(oracleData.getValidationQuery());
            // 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
            datasource.setTestWhileIdle(oracleData.isTestWhileIdle());
            // 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
            datasource.setTestOnBorrow(oracleData.isTestOnBorrow());
            // 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
            datasource.setTestOnReturn(oracleData.isTestOnReturn());
            // 打开PSCache，并且指定每个连接上PSCache的大小
            datasource.setPoolPreparedStatements(oracleData.isPoolPreparedStatements());
            datasource.setMaxPoolPreparedStatementPerConnectionSize(oracleData.getMaxPoolPreparedStatementPerConnectionSize());
            // 配置监控统计拦截的filters，去掉后监控界面sql无法统计:监控统计用的filter:stat;日志用的filter:log4j;防御sql注入的filter:wall'wall'用于防火墙
            datasource.setFilters(oracleData.getFilters());
            // 通过connectProperties属性来打开mergeSql功能；慢SQL记录
            datasource.setConnectionProperties(oracleData.getConnectionProperties());
        }
        catch (final SQLException e) {
            log.error("ORACLE数据库初始化中出现问题", e);
        }
        return datasource;
    }

}
