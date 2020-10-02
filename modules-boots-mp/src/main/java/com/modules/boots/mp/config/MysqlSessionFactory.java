/**
 * All rights Reserved, Designed By OprCalf
 * Copyright:    Copyright(C) 2016-2020
 * Company       BSL Ltd.
 */

package com.modules.boots.mp.config;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.config.GlobalConfig.DbConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.modules.boots.mp.CustomIdGenerator;
import com.modules.boots.mp.FiledsFillingHandler;

/**
 * @projectName:  widget-db-boot-starter
 * @package:      com.widget.db.config
 * @className:    SqlSessionConfig.java
 * @description:  SqlSession配置
 * @author:       OprCalf
 * @date:         2019年1月15日
 */
@MapperScan(basePackages = "com.bsl.**.business.bscm.**.dao", sqlSessionTemplateRef = "bscmTemplate")
@EnableTransactionManagement
@Configuration
public class MysqlSessionFactory {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomIdGenerator customIdGenerator;

    // 数据库查询后获取的对象存放目录,包含单表查询对象及多表查询对象
    private final static String typeAliasPackage = "com.boots.**.business.model";

    // 数据库查询的xml文件配置
    private final static String mapperPath = "classpath:/mappers/*Mapper.xml";

    /**
     * 添加分页插件支持
     * @author 林溪
     * @return PaginationInterceptor
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        final MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 新增MYSQL分页拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * mybatisplus数据层配置
     * @author 林溪
     * @return DbConfig
     */
    @Bean
    public DbConfig dbConfig() {
        final DbConfig dbConfig = new DbConfig();
        // 设置ID的生成规则
        dbConfig.setIdType(IdType.ASSIGN_ID);
        // 设置表名是否使用下划线命名
        dbConfig.setTableUnderline(true);
        // 字段插入非空判断
        dbConfig.setInsertStrategy(FieldStrategy.NOT_EMPTY);
        // 字段更新非空判断
        dbConfig.setUpdateStrategy(FieldStrategy.NOT_EMPTY);
        // 字段查询非空判断
        dbConfig.setSelectStrategy(FieldStrategy.NOT_EMPTY);
        return dbConfig;
    }

    /**
     * mybatisplus全局配置
     * @author 林溪
     * @param dbConfig
     * @return GlobalConfig
     */
    @Bean
    public GlobalConfig globalConfig(DbConfig dbConfig) {
        final GlobalConfig globalConfig = new GlobalConfig();
        // 设置mybatisplus数据层配置
        globalConfig.setDbConfig(dbConfig);
        // 初始化SqlRunner
        globalConfig.setEnableSqlRunner(true);
        // 设置自定义主键ID的生成方式
        globalConfig.setIdentifierGenerator(customIdGenerator);
        // 加载字段自动填充器
        globalConfig.setMetaObjectHandler(new FiledsFillingHandler());
        return globalConfig;
    }

    /**
     * 配置mybatis
     * @author 林溪
     * @return MybatisConfiguration
     */
    @Bean
    public MybatisConfiguration mybatisConfiguration() {
        final MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        // 设置为XML语言驱动
        mybatisConfiguration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        // 设置数据库字段值映射方式,例如：updatedId在进行DB操作的时候会被自动解析为updated_id
        mybatisConfiguration.setMapUnderscoreToCamelCase(true);
        // 开启Mybatis的二级缓存
        mybatisConfiguration.setCacheEnabled(true);
        // 当查询的返回一行都是null的结果时，MyBatis会帮忙填充一个所有属性都是null的对象
        mybatisConfiguration.setCallSettersOnNulls(true);
        return mybatisConfiguration;
    }

    /**
     * 配置mybatis连接的session工厂
     * @author 林溪
     * @param globalConfig
     * @param mybatisConfiguration
     * @param paginationInterceptor
     * @return
     * @throws Exception MybatisSqlSessionFactoryBean
     */
    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactory(GlobalConfig globalConfig,
            MybatisConfiguration mybatisConfiguration, MybatisPlusInterceptor paginationInterceptor) throws Exception {
        final MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 设置XML的映射路径
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperPath));
        // 设置实体类扫描路径
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasPackage);
        // 设置mybatisplus全局配置
        sqlSessionFactoryBean.setGlobalConfig(globalConfig);
        // 设置mybatis的配置
        sqlSessionFactoryBean.setConfiguration(mybatisConfiguration);
        // 加载插件
        sqlSessionFactoryBean.setPlugins(paginationInterceptor);
        return sqlSessionFactoryBean;
    }
}
